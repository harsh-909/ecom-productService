package com.ecom.productService.controller;

import com.ecom.productService.models.Category;
import com.ecom.productService.models.Product;
import com.ecom.productService.service.ProductService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private ProductService productService;

    @Test
    void getAllProducts() {


//        List<Product> products = new ArrayList<>();
//
//        Product p1 = new Product();
//        p1.setCategory(new Category());
//        p1.setProductName("bottle");
//        products.add(p1);
//
//        Product p2 = new Product();
//        p2.setCategory(new Category());
//        p2.setProductName("bag");
//        products.add(p2);
//
//        Product p3 = new Product();
//        p3.setCategory(new Category());
//        p3.setProductName("kuch to hai");
//        products.add(p3);
//
//        when(productService.getAllProducts()).thenReturn(products);
//
//        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();
//
//        assertEquals(200, responseEntity.getStatusCode().value());
//        assert responseEntity.getBody() != null;
//        assertEquals(3, responseEntity.getBody().size());
//        for(int i= 0;i<products.size();i++){
//            assertEquals(products.get(i).getProductName(), responseEntity.getBody().get(i).getProductName());
//        }
    }
}