package com.andrew.budgetTracker.model;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long TransactionID;
    private double amount;
    private TransactionType type;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    public enum TransactionType {
        income, expense
    }

    public Transaction(){}

    public Transaction(TransactionType type, double amount){
        this.type = type;
        this.amount = amount;
    }

    public long getTransactionID() {
        return TransactionID;
    }

    public TransactionType getType() {
        return this.type;
    }

    public double getAmount(){
        return this.amount;
    }

}
