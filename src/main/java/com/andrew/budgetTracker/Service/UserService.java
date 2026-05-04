package com.andrew.budgetTracker.Service;

import com.andrew.budgetTracker.Exceptions.UserNotFoundException;
import com.andrew.budgetTracker.model.User;
import com.andrew.budgetTracker.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    userRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(long userID){
        return userRepo.findById(userID).orElseThrow(() -> new UserNotFoundException());
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(long userID){
        userRepo.deleteById(userID);
    }

    public User updateUser(long userID, User newUser){
        User user = userRepo.findById(userID).orElseThrow(() -> new UserNotFoundException());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setEmailAddress(newUser.getEmailAddress());
        return userRepo.save(user);
    }
}
