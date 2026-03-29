package com.ecom.productService.commonUtils;

import com.ecom.productService.dto.FakeStoreCategoryDto;
import com.ecom.productService.models.Category;

public class CategoryMapper {

    public static FakeStoreCategoryDto convertCategoryToFakeStoreCategoryDto(Category category){

        return new FakeStoreCategoryDto(category.getCategoryId(), category.getCategoryName());
    }

    public static Category convertFakeStoreCategoryDtoToCatgory(FakeStoreCategoryDto fakeStoreCategoryDto){
        Category category = new Category();
        category.setCategoryName(fakeStoreCategoryDto.getCategoryName());
        category.setCategoryId(fakeStoreCategoryDto.getCategoryId());
        return category;
    }
}
