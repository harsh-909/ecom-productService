package com.ecom.productService.controller;


import com.ecom.productService.Exception.InvalidTokenException;
import com.ecom.productService.Exception.ProductNotFoundException;
import com.ecom.productService.Exception.UserNotFoundException;
import com.ecom.productService.dto.UserTokenDto;
import com.ecom.productService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecom.productService.service.ProductService;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;
    private final RestTemplate restTemplate;

    @Autowired
    ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate){
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products = productService.getAllProducts();

        List<Product> finalProducts = new ArrayList<>();

        for( Product product :  products){
            Product p1 = new Product(product);
            p1.setProductName("Hello" + p1.getProductName());
            finalProducts.add(p1);

        }
        return  new ResponseEntity<>(
                finalProducts, HttpStatus.OK);
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

    @PostMapping("/validateToken")
    public ResponseEntity<UserTokenDto> validateToken(@RequestHeader("Authorization") String token) throws InvalidTokenException, UserNotFoundException {
        ResponseEntity<UserTokenDto> responseEntity = restTemplate.postForEntity("http://localhost:8080/users/validateToken"
                ,new HttpEntity<>(new HttpHeaders(){{
                    set("Authorization", token);
                }}),
                UserTokenDto.class, token);

        if(responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new InvalidTokenException("Invalid Token");
        }
        if(responseEntity.getBody() == null) {
            throw new UserNotFoundException("Invalid User");
        }

        return responseEntity;
    }
}
