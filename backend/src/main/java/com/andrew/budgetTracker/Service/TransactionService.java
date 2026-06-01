package com.andrew.budgetTracker.Service;

import com.andrew.budgetTracker.Exceptions.TransactionNotFoundException;
import com.andrew.budgetTracker.Exceptions.UserNotFoundException;
import com.andrew.budgetTracker.model.Category;
import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.model.User;
import com.andrew.budgetTracker.repository.CategoryRepo;
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

    @Autowired
    CategoryService categoryService;

    public Transaction getTransactionById(long transactionID){
        return transactionRepo.findById(transactionID).orElseThrow(() -> new TransactionNotFoundException());
    }

    public List<Transaction> getTransactionsByEmail(String email){
        return transactionRepo.findByEmailAddress(email);
    }

    public Transaction saveTransaction(long userID, Transaction transaction) {
        String cat = transaction.getCategoryName();
        User currUser = userRepo.findById(userID).orElseThrow(() -> new UserNotFoundException());
        Category category = categoryService.createCategoryIfNotExist(cat);

        transaction.setCategory(category);
        currUser.addTransaction(transaction);

        if (transaction.getType().equals(Transaction.TransactionType.income)){
            currUser.setAccAmt(currUser.getAccAmt() + transaction.getAmount());
        } else if (transaction.getType().equals(Transaction.TransactionType.expense)) {
            currUser.setAccAmt(currUser.getAccAmt() - transaction.getAmount());
        }

        return transactionRepo.save(transaction);
    }

    public void deleteTransaction(long transactionID){
        transactionRepo.deleteById(transactionID);
    }
}
