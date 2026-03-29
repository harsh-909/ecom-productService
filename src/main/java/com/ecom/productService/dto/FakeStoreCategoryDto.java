package com.ecom.productService.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCategoryDto {

    public FakeStoreCategoryDto(long categoryId, String categoryName){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public FakeStoreCategoryDto(String categoryName){
        this.categoryName = categoryName;
    }

    private long categoryId;
    private String categoryName;
}
