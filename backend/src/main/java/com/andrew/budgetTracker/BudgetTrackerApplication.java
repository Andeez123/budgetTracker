package com.andrew.budgetTracker;

import com.andrew.budgetTracker.model.Category;
import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.model.User;
import com.andrew.budgetTracker.repository.CategoryRepo;
import com.andrew.budgetTracker.repository.TransactionRepo;
import com.andrew.budgetTracker.repository.userRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.*;

@SpringBootApplication
public class BudgetTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetTrackerApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner runApp(userRepo userRepo, TransactionRepo transactionRepo, CategoryRepo categoryRepo){
//		User u1 = new User("Andrew", "Chong", "0162238805", "andeeznutz003@gmail.com", "TestPassword",1000.0);
////		Transaction t1 = new Transaction(Transaction.TransactionType.expense, 100);
////		Transaction t2 = new Transaction(Transaction.TransactionType.income, 200);
//
//		Category foodCat = new Category("Food");
//		Category clothingCat = new Category("Clothing");
//		return args -> {
//			userRepo.save(u1);
//			categoryRepo.save(foodCat);
//			categoryRepo.save(clothingCat);
//		};
//	}

}
