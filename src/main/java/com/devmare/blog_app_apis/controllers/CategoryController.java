package com.devmare.blog_app_apis.controllers;

import com.devmare.blog_app_apis.payloads.AppApiResponse;
import com.devmare.blog_app_apis.payloads.dto.CategoryDTO;
import com.devmare.blog_app_apis.services.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "scheme1")
@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category Controller", description = "This contains Category related API methods")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //! http://localhost:8081/api/categories/
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO newCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    //! http://localhost:8081/api/categories/{categoryId}
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId) {
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    //! http://localhost:8081/api/categories/{categoryId}
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<AppApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(
                new AppApiResponse("Category deleted successfully", true),
                HttpStatus.OK
        );
    }

    //! http://localhost:8081/api/categories/
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOSList = categoryService.getCategoryList();
        return ResponseEntity.ok(categoryDTOSList);
    }

    //! http://localhost:8081/api/categories/{categoryId}
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }
}
