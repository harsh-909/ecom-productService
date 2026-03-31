package com.ecom.productService.serviceImpl;

import com.ecom.productService.Exception.ProductNotFoundException;
import com.ecom.productService.models.Product;
import com.ecom.productService.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService {


    @Override
    public Product getSingleProduct(long productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateAProduct(long productId, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void deleteAProduct(long productId) throws ProductNotFoundException {

    }
}
