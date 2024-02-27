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
    private Long billId;
    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();
    private Double amount;
    private Date billGeneratedDate;

    private Date dueDate;
    private boolean isPaid;
    private String cardNumber;

    public Bill() {
    }

    public Bill(String cardNumber, List<Transaction> transactions, Double amount, Date billGeneratedDate, Date dueDate, boolean isPaid) {
        this.cardNumber=cardNumber;
        this.transactions = transactions;
        this.amount = amount;
        this.billGeneratedDate = billGeneratedDate;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long id) {
        this.billId = id;
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

    public Date getBillGeneratedDate() {
        return billGeneratedDate;
    }

    public void setBillGeneratedDate(Date billGeneratedDate) {
        this.billGeneratedDate = billGeneratedDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
