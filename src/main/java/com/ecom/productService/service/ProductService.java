package com.ecom.productService.service;

import com.ecom.productService.Exception.ProductNotFoundException;
import com.ecom.productService.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(long productId) throws ProductNotFoundException;

    Product addNewProduct(Product product);

    List<Product> getAllProducts();

    Product updateAProduct(long productId, Product product) throws ProductNotFoundException;

    void deleteAProduct(long productId) throws ProductNotFoundException;
}
