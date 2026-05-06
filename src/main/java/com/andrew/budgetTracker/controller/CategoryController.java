package com.andrew.budgetTracker.controller;

import com.andrew.budgetTracker.Service.CategoryService;
import com.andrew.budgetTracker.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category savedCategory = categoryService.createCategoryIfNotExist(category.getName());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand(savedCategory.getCategoryID()).toUri();
        return ResponseEntity.created(location).body(savedCategory);
    }

    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.findAllCategory();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long categoryId){
        Category category = categoryService.getCategory(categoryId);
        String categoryName = category.getName();
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category " + categoryName + " has been deleted");
    }
}
