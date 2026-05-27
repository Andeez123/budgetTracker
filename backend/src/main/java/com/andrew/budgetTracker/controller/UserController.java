package com.andrew.budgetTracker.controller;

import com.andrew.budgetTracker.Service.UserService;
import com.andrew.budgetTracker.model.LoginDTO;
import com.andrew.budgetTracker.model.Transaction;
import com.andrew.budgetTracker.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    public UserController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder){
        this.authManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO userRequest){
//        User user = userService.getUserByEmail(userRequest.getEmail());
//        System.out.println("Email received: '" + userRequest.getEmail() + "'");
//        System.out.println("Password received: '" + userRequest.getPassword() + "'");
//        if (user.getPassword().equals(userRequest.getPassword())){
//            return ResponseEntity.ok("Login Successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getEmail(),
                        userRequest.getPassword()
                )
        );
        return ResponseEntity.ok().build();
    }
}
