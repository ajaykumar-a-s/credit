package com.project.credit.bill.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.credit.card.entity.CreditCard;
import com.project.credit.customer.entity.Customer;
import com.project.credit.transaction.entity.Transaction;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();

    public Bill() {
    }

    public Bill(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
