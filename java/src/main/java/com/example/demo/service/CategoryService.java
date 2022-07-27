package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<Category> getCategory(int id);
    ResponseEntity<List<Category>> findCategoryByNameContainsOrderByName(String name);
    ResponseEntity<ApiResponse> deleteCategory(int id);
    ResponseEntity<Category> addCategory(Category category);
    ResponseEntity<ApiResponse> updateCategory(Category category);
    ResponseEntity<List<Category>> getCategories(int page, int size);
}
