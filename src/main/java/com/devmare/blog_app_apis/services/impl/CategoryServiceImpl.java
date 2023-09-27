package com.devmare.blog_app_apis.services.impl;

import com.devmare.blog_app_apis.configuration.ModelMapperConfiguration;
import com.devmare.blog_app_apis.entities.Category;
import com.devmare.blog_app_apis.exceptions.ResourceNotFoundException;
import com.devmare.blog_app_apis.payloads.CategoryDTO;
import com.devmare.blog_app_apis.repositories.CategoryRepository;
import com.devmare.blog_app_apis.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Category category = modelMapperConfiguration.modelMapper().map(categoryDto, Category.class);
        Category createdCategory = categoryRepository.save(category);
        return modelMapperConfiguration.modelMapper().map(createdCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId) {
        Category currentCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category ", "id: ", categoryId)
        );
        currentCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        currentCategory.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepository.save(currentCategory);
        return modelMapperConfiguration.modelMapper().map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category currentCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category ", "id: ", categoryId)
        );
        categoryRepository.delete(currentCategory);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category currentCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category ", "id: ", categoryId)
        );
        return modelMapperConfiguration.modelMapper().map(currentCategory, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(
                        category -> modelMapperConfiguration
                                .modelMapper()
                                .map(categoryList, CategoryDTO.class)
                ).collect(Collectors.toList());
    }
}
