package com.ecom.productService.models;

import lombok.Data;

@Data
public class Product {

    private long productId;
    private String productName;
    private double price;
    private Category category;
    private String description;
    private String imageUrl;
}
