package com.ecom.productService.controller;


import com.ecom.productService.models.Category;
import com.ecom.productService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public Category getSingleCategory(@PathVariable("categoryId") Long id){
        return categoryService.getACategory(id);
    }

    @PostMapping("/")
    public Category addNewProduct(@RequestBody Category category){
        return categoryService.addACategory(category);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteACategory(@PathVariable long categoryId){
        categoryService.deleteACategory(categoryId);
    }
}
