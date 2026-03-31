package com.ecom.productService.serviceImpl;

import com.ecom.productService.Exception.ProductNotFoundException;
import com.ecom.productService.commonUtils.ProductMapper;
import com.ecom.productService.dto.FakeStoreProductDto;
import com.ecom.productService.models.Product;
import com.ecom.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    private ProductMapper productMapper;

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );

        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("Product with id " + productId + " not found.");
        }
        return ProductMapper.convertFakeStoreProductToProduct(fakeStoreProductDto);

    }

    @Override
    public Product addNewProduct(Product product) {

        FakeStoreProductDto fakeStoreProductDto = ProductMapper.convertProductToFakeStoreProduct(product);

        FakeStoreProductDto fakeStoreProductDtoResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );

        if (fakeStoreProductDtoResponse == null) {
            throw new RuntimeException("Failed to add new product.");
        }
        return ProductMapper.convertFakeStoreProductToProduct(fakeStoreProductDtoResponse);
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        if (fakeStoreProductDtos == null) {
            return List.of();
        }
        return Arrays.stream(fakeStoreProductDtos).map(ProductMapper::convertFakeStoreProductToProduct).toList();
    }

    @Override
    public Product updateAProduct(long productId, Product product) throws ProductNotFoundException {

        Product oldProduct = getSingleProduct(productId);
        updateToNewProduct(oldProduct, product);

        FakeStoreProductDto fakeStoreProductDto = ProductMapper.convertProductToFakeStoreProduct(oldProduct);
        restTemplate.put(
                "https://fakestoreapi.com/products/" + productId,
                fakeStoreProductDto
        );

        return oldProduct;
    }

    @Override
    public void deleteAProduct(long productId) throws ProductNotFoundException {
        getSingleProduct(productId); // throws ProductNotFoundException if not found
        restTemplate.delete("https://fakestoreapi.com/products/" + productId);
    }

    void updateToNewProduct(Product oldProduct, Product newProduct) {
        if (newProduct.getProductName() != null) {
            oldProduct.setProductName(newProduct.getProductName());
        }
        if (newProduct.getDescription() != null) {
            oldProduct.setDescription(newProduct.getDescription());
        }
        if (newProduct.getPrice() != 0) {
            oldProduct.setPrice(newProduct.getPrice());
        }
        if (newProduct.getCategory() != null) {
            oldProduct.setCategory(newProduct.getCategory());
        }
    }

}
