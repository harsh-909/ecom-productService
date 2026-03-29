package com.ecom.productService.Exception;


import com.ecom.productService.dto.ExceptionDto;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message){
        super(message);
    }

}
