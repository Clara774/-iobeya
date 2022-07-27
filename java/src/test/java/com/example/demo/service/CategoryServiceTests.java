package com.example.demo.service;

import com.example.demo.Demo1Application;
import com.example.demo.controller.CategoryController;
import com.example.demo.model.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class CategoryServiceTests {
    @Autowired
    private CategoryController categoryController;

    @Test
    public void getCategoryByName() {
        //String name = "test";

        //ResponseEntity<List<Category>> category = this.categoryController.getAllByNameLike(name);

        //Assert.assertNotNull(category);
        //Assert.assertNotNull(category.getBody());
        //Assert.assertEquals(2, category.getBody().size());
    }

    @Test
    public void getCategoryTest() {
        /*int id = 3;

        ResponseEntity<Category> category = this.categoryController.getCategory(id);

        Category parent = category.getBody().getParent();

        Assert.assertNotNull(parent);

        List<Category> child = category.getBody().getChildren();
        Assert.assertEquals(2, child.size());*/


        //org.junit.Assert.assertEquals("test2", category.getBody().getName());
    }
}
