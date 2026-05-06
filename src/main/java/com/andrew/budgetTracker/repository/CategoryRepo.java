package com.andrew.budgetTracker.repository;

import com.andrew.budgetTracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query("select c from Category c")
    List<Category> findAll();
}
