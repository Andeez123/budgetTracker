package com.andrew.budgetTracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryID;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions = new ArrayList<>();

    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public String getName(){return this.name;}

}
