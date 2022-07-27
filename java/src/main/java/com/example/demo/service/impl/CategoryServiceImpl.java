package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.payload.ApiResponse;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<Category> getCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> findCategoryByNameContainsOrderByName(String name) {
        return new ResponseEntity<>(categoryRepository.findCategoryByNameContainsOrderByName(name), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCategory(int id) {
        try {
            categoryRepository.deleteById(id);
            categoryRepository.flush();
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "Category deleted"), HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println(e.getClass().toString());
            return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, "Category delete failed"), HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<Category> addCategory(Category category) {
        try {
            Category newCategory = categoryRepository.saveAndFlush(category);
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updateCategory(Category category) {
        try {
            categoryRepository.saveAndFlush(category);
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "Category updated"), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(Boolean.FALSE, "Category updated failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Category>> getCategories(int page, int size) {
        page = page < 0 ? 0 : page;
        size = size < 0 || size > 20 ? 20 : size;

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "name");
        Page<Category> pageCategories = categoryRepository.findAll(pageable);

        List<Category> categories = new ArrayList<>(pageCategories.getContent().size());

        pageCategories.getContent().forEach(c -> categories.add(c));

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
