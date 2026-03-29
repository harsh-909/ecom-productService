package com.ecom.productService.serviceImpl;

import com.ecom.productService.commonUtils.CategoryMapper;
import com.ecom.productService.dto.FakeStoreCategoryDto;
import com.ecom.productService.models.Category;
import com.ecom.productService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final RestTemplate restTemplate;

    @Autowired
    public CategoryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Category addACategory(Category category) {

        FakeStoreCategoryDto fakeStoreCategoryDto = CategoryMapper.convertCategoryToFakeStoreCategoryDto(category);
        FakeStoreCategoryDto fakeStoreCategoryDtoResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/categories",
                fakeStoreCategoryDto,
                FakeStoreCategoryDto.class
        );

        return CategoryMapper.convertFakeStoreCategoryDtoToCatgory(fakeStoreCategoryDto);
    }

    @Override
    public Category getACategory(long categoryId) {

        FakeStoreCategoryDto fakeStoreCategoryDto = restTemplate.getForObject(
                "https://fakestoreapi.com/categories" + categoryId,
                FakeStoreCategoryDto.class
        );

        assert fakeStoreCategoryDto != null;
        return CategoryMapper.convertFakeStoreCategoryDtoToCatgory(fakeStoreCategoryDto);
    }

    @Override
    public List<Category> getAllCategories() {

        FakeStoreCategoryDto[] fakeStoreCategoryDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/categories",
                FakeStoreCategoryDto[].class
        );

        assert fakeStoreCategoryDtos != null;
        return Arrays.stream(fakeStoreCategoryDtos).map(CategoryMapper::convertFakeStoreCategoryDtoToCatgory).toList();
    }

    @Override
    public void deleteACategory(long categoryId) {

        restTemplate.delete(
                "https://fakestoreapi.com/categories" + categoryId
        );
    }
}
