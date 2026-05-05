package com.andrew.budgetTracker.Service;

import com.andrew.budgetTracker.Exceptions.TransactionNotFoundException;
import com.andrew.budgetTracker.Exceptions.UserNotFoundException;
import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.model.User;
import com.andrew.budgetTracker.repository.TransactionRepo;
import com.andrew.budgetTracker.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    userRepo userRepo;

    public Transaction getTransactionById(long transactionID){
        return transactionRepo.findById(transactionID).orElseThrow(() -> new TransactionNotFoundException());
    }

    public List<Transaction> getTransactionsByUserID(long userID){
        return transactionRepo.findByUserID(userID);
    }

    public Transaction saveTransaction(long userID, Transaction transaction) {
        User currUser = userRepo.findById(userID).orElseThrow(() -> new UserNotFoundException());
        currUser.addTransaction(transaction);
        if (transaction.getType().equals(Transaction.TransactionType.income)){
            currUser.setAccAmt(currUser.getAccAmt() + transaction.getAmount());
        } else if (transaction.getType().equals(Transaction.TransactionType.expense)) {
            currUser.setAccAmt(currUser.getAccAmt() - transaction.getAmount());
        }

        return transactionRepo.save(transaction);
    }
}
