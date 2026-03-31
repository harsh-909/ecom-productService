package com.ecom.productService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{

    private String productName;
    private double price;
    @ManyToOne
    private Category category;
    private String description;
    private String imageUrl;
}
