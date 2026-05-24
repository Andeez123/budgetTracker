package com.andrew.budgetTracker.controller;

import com.andrew.budgetTracker.Service.UserService;
import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Gets all users from in the system.")
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @Operation(summary = "Gets user by specified ID.")
    @GetMapping("{id}")
    public User getUser(@PathVariable("id") long userID){
        return userService.getUserById(userID);
    }

    @Operation(summary = "Creates a user.")
    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserID()).toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @Operation(summary = "Deletes a user from the database by ID.")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userID){
        userService.deleteUser(userID);
        return ResponseEntity.ok("User" + userID + " deleted");
    }

    @Operation(summary = "Updates specified user with new user data.")
    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long userID, @RequestBody User newUser){
        User savedUser = userService.updateUser(userID, newUser);
        return ResponseEntity.ok("User" + userID + " updated");
    }
}
