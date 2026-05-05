package com.andrew.budgetTracker.Service;

import com.andrew.budgetTracker.model.Category;
import com.andrew.budgetTracker.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public Category createCategoryIfNotExist(String cat){
        Category category = null;
        cat = cat.substring(0,1).toUpperCase() + cat.substring(1);
        try {
            category = categoryRepo.findByName(cat).get();
        } catch (RuntimeException e){

        }

        if (category != null){
            return category;
        } else {
            category = new Category(cat);
            categoryRepo.save(category);
            return category;
        }
    }
}
