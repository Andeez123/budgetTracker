package com.andrew.budgetTracker.Service;

import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepo transactionRepo;

    public List<Transaction> getTransactionById(long userID){
        return transactionRepo.findById(userID);
    }
}
