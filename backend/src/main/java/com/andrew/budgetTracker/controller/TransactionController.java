package com.andrew.budgetTracker.controller;

import com.andrew.budgetTracker.Service.TransactionService;
import com.andrew.budgetTracker.model.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Operation(summary = "Return transaction by ID")
    @GetMapping("{id}")
    public Transaction getTransactionById(@PathVariable("id") long userID){
        return transactionService.getTransactionById(userID);
    }

    @GetMapping("/user/{id}")
    public List<Transaction> getTransactionByUserID(@PathVariable("id") long userID){
        return transactionService.getTransactionsByUserID(userID);
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<Transaction> addTransaction(@PathVariable("id") long userID, @RequestBody Transaction transaction){
        Transaction savedTransaction = transactionService.saveTransaction(userID, transaction);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("").buildAndExpand(savedTransaction.getTransactionID()).toUri();
        return ResponseEntity.created(location).body(savedTransaction);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTransactionByID(@PathVariable("id") long transactionID){
        Transaction transaction = transactionService.getTransactionById(transactionID);
        transactionService.deleteTransaction(transactionID);
        return ResponseEntity.ok("Deleted: " + transaction.toString());
    }
}
