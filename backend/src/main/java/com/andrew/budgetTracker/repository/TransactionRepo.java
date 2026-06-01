package com.andrew.budgetTracker.repository;


import com.andrew.budgetTracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("select u.transactions from User u where u.emailAddress = :email")
    List<Transaction> findByEmailAddress(@Param("email") String email);
}
