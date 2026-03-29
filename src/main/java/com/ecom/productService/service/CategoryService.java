package com.ecom.productService.service;

import com.ecom.productService.models.Category;

import java.util.List;

public interface CategoryService {

    public Category addACategory(Category category);

    public Category getACategory(long categoryId);

    public List<Category> getAllCategories();

    public void deleteACategory(long categoryId);
}
