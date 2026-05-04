package com.andrew.budgetTracker;

import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.model.User;
import com.andrew.budgetTracker.repository.TransactionRepo;
import com.andrew.budgetTracker.repository.userRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BudgetTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetTrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner runApp(userRepo userRepo, TransactionRepo transactionRepo){
		User u1 = new User("Andrew", "Chong", "0162238805", "andeeznutz003@gmail.com", 0);
		Transaction t1 = new Transaction(Transaction.TransactionType.expense, 100);
		Transaction t2 = new Transaction(Transaction.TransactionType.income, 200);
		return args -> {
			userRepo.save(u1);
			transactionRepo.save(t1);
			transactionRepo.save(t2);
			u1.addTransaction(t1);
			u1.addTransaction(t2);
		};
	}

}
