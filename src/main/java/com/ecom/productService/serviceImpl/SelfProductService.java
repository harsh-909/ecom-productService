package com.ecom.productService.serviceImpl;

import com.ecom.productService.Exception.ProductNotFoundException;
import com.ecom.productService.models.Category;
import com.ecom.productService.models.Product;
import com.ecom.productService.repositories.CategoryRepository;
import com.ecom.productService.repositories.ProductRepository;
import com.ecom.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(!optionalProduct.isPresent()){
            throw new ProductNotFoundException("Product with "+ productId + " is not present");
        }
        return optionalProduct.get();
    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByCategoryNameAllIgnoreCase(category.getCategoryName()));

        if(optionalCategory.isEmpty()){
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }else{
            product.setCategory(optionalCategory.get());
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateAProduct(long productId, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()) throw new ProductNotFoundException("Product with id " + productId + " is not present");

        Product savedProduct = productOptional.get();

        if(product.getProductName() != null) savedProduct.setProductName(product.getProductName());
        if(product.getPrice() != null) savedProduct.setPrice(product.getPrice());
        if(product.getDescription() != null) savedProduct.setDescription(product.getDescription());
        if(product.getCategory() != null) {
            Category category = product.getCategory();
            Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByCategoryNameAllIgnoreCase(category.getCategoryName()));
            if(optionalCategory.isPresent()){
                if(!optionalCategory.get().getCategoryName().equalsIgnoreCase(category.getCategoryName())){
                    savedProduct.setCategory(optionalCategory.get());
                }
            }
            else{
                Category savedCategory = categoryRepository.save(category);
                product.setCategory(savedCategory);
            }
        }
        if(product.getImageUrl() != null) savedProduct.setImageUrl(product.getImageUrl());
        return savedProduct;
    }

    @Override
    public void deleteAProduct(long productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()) throw new ProductNotFoundException("Product with id " + productId + " is not present");
        productRepository.deleteById(productId);
    }
}
