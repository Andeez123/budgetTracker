package com.andrew.budgetTracker.repository;

import com.andrew.budgetTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User, Long> {
}
