package com.andrew.budgetTracker.repository;

import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface userRepo extends JpaRepository<User, Long> {
//    @Query("select u from User u where u.emailAddress = :email")
    Optional<User> findByEmailAddress(String emailAddress);
}
