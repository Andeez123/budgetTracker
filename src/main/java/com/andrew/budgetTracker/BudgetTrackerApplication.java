package com.andrew.budgetTracker;

import com.andrew.budgetTracker.model.User;
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
	public CommandLineRunner runApp(userRepo userRepo){
		User u1 = new User("Andrew", "Chong", "0162238805", "andeeznutz003@gmail.com");

		return args -> {
			userRepo.save(u1);
		};
	}

}
