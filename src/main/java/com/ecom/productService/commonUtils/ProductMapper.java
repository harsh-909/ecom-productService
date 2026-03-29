package com.ecom.productService.commonUtils;

import com.ecom.productService.dto.FakeStoreProductDto;
import com.ecom.productService.models.Category;
import com.ecom.productService.models.Product;

public class ProductMapper {

    public static Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();

        product.setProductId(fakeStoreProductDto.getId());
        product.setProductName(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(new Category());
        product.getCategory().setCategoryName(fakeStoreProductDto.getCategory());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }

    public static FakeStoreProductDto convertProductToFakeStoreProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setId(product.getProductId());
        fakeStoreProductDto.setTitle(product.getProductName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getCategoryName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        return fakeStoreProductDto;
    }

}
