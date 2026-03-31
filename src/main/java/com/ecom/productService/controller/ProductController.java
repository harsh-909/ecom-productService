package com.ecom.productService.controller;


import com.ecom.productService.Exception.ProductNotFoundException;
import com.ecom.productService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecom.productService.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @Autowired
    ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.OK);
    }

    // if exception not thrown here, it will throw it to the controller advices which will
    // catch the exception if that particular exception handler is defined.
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId){

        try{
            return new ResponseEntity<>(productService.getSingleProduct(productId),
                    HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addNewProduct(product),
                HttpStatus.OK);

    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Product> updateAProduct(@PathVariable("productId") long productId,
                                  @RequestBody Product product) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.updateAProduct(productId,product),
                HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public void deleteAProduct(@PathVariable long productId) throws ProductNotFoundException {
        productService.deleteAProduct(productId);
    }
}
