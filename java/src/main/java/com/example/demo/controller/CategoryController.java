package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Category> getCategory(@PathVariable(name = "id") int id) {
        return categoryService.getCategory(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Category>> getCategories(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return categoryService.getCategories(page, size);
    }

    @GetMapping("search/{name}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Category>> getAllByNameLike(@PathVariable(name = "name") String name) {
        return categoryService.findCategoryByNameContainsOrderByName(name);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(name = "id") int id) {
        return categoryService.deleteCategory(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
}
