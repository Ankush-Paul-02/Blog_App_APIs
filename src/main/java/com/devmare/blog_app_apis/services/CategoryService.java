package com.devmare.blog_app_apis.services;

import com.devmare.blog_app_apis.payloads.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    //? Create Category
    CategoryDTO createCategory(CategoryDTO categoryDto);

    //? Update Category
    CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId);

    //? Delete Category
    void deleteCategory(Integer categoryId);

    //? Get Category
    CategoryDTO getCategory(Integer categoryId);

    //? Get All Categories
    List<CategoryDTO> getCategoryList();
}
