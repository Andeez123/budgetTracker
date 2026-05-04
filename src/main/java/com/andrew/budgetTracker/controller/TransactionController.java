package com.andrew.budgetTracker.controller;

import com.andrew.budgetTracker.Service.TransactionService;
import com.andrew.budgetTracker.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("{id}")
    public List<Transaction> getAllTransactionsByUser(@PathVariable("id") long userID){
        return transactionService.getTransactionById(userID);
    }
}
