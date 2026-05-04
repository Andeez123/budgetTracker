package com.andrew.budgetTracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private double accAmt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public User(){}

    public User(String fName, String lName, String pNum, String email, double amt){
        this.firstName = fName;
        this.lastName = lName;
        this.phoneNumber = pNum;
        this.emailAddress = email;
        this.accAmt = amt;
    }

    public long getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

}
