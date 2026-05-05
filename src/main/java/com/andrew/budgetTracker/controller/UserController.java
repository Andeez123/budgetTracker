package com.andrew.budgetTracker.controller;

import com.andrew.budgetTracker.Service.UserService;
import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") long userID){
        return userService.getUserById(userID);
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserID()).toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userID){
        userService.deleteUser(userID);
        return ResponseEntity.ok("User" + userID + " deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long userID, @RequestBody User newUser){
        User savedUser = userService.updateUser(userID, newUser);
        return ResponseEntity.ok("User" + userID + " updated");
    }
}
