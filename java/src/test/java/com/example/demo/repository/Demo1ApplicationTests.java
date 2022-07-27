package com.example.demo.repository;

import com.example.demo.Demo1Application;
import com.example.demo.model.Category;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = Demo1Application.class)
public class Demo1ApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void getCategoryByName() {
        ResponseEntity<List<Category>> result = categoryService.findCategoryByNameContainsOrderByName("4.");
        Assert.assertNotNull(result);
    }

    @Test
    public void getCategoryById() {
        ResponseEntity<Category> result = categoryService.getCategory(3);
        Assert.assertEquals("category.2", result.getBody().getName());
    }

    @Test
    public void getAllCategory() {
        ResponseEntity<List<Category>> result = categoryService.getCategories(1,10);
        Assert.assertEquals(10, result.getBody().size());
    }

    @Test
    public void addCategory() {
        Category category = new Category();
        category.setName("tutu");
        category.setParent(9);
        ResponseEntity<Category> result = categoryService.addCategory(category);
        Assert.assertEquals("tutu", result.getBody().getName());
    }

    @Test
    public void deleteCategory() {
        ResponseEntity<ApiResponse> result = categoryService.deleteCategory(1);
        Assert.assertEquals("Category deleted", result.getBody().getMessage());
    }

    @Test
    public void deleteCategoryError() {
        ResponseEntity<ApiResponse> result = categoryService.deleteCategory(11);
        Assert.assertEquals("Category delete failed", result.getBody().getMessage());
    }

    @Test
    public void updateCategory() {
        Category category = new Category();
        category.setId(7);
        category.setName("category.0.1.2");
        category.setParent(3);
        ResponseEntity<ApiResponse> result = categoryService.updateCategory(category);
        Assert.assertEquals("Category updated", result.getBody().getMessage());
    }
}
