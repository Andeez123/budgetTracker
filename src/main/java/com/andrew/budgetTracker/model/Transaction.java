package com.andrew.budgetTracker.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long TransactionID;
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    private String categoryName;

    @CreationTimestamp
    private Instant createdOn;

    public enum TransactionType {
        income, expense
    }

    public Transaction(){}

    public Transaction(TransactionType type, double amount, String category){
        this.transactionType = type;
        this.amount = amount;
        this.categoryName = category;
    }

    public long getTransactionID() {
        return TransactionID;
    }

    public TransactionType getType() {
        return this.transactionType;
    }

    public double getAmount(){
        return this.amount;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){return this.user;}

    public void setCategory(Category category){
        this.category = category;
    }

    public String getCategoryName(){
        return this.categoryName;
    }

    public Instant getCreatedOn(){return this.createdOn;}

    @Override
    public String toString() {
        return "Transaction{" +
                "TransactionID=" + TransactionID +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", user=" + user +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
