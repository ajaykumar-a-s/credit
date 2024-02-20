package com.project.credit.bill.entity;

import com.project.credit.transaction.entity.Transaction;
import jakarta.persistence.*;

import java.sql.Date;
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

    private Date dueDate;
    private boolean isPaid;

    public Bill() {
    }

    public Bill(String name, Date dueDate, boolean isPaid) {
        this.name = name;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
