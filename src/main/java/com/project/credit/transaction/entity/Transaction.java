package com.project.credit.transaction.entity;


import com.project.credit.card.entity.CreditCard;
import com.project.credit.merchant.entity.Merchant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


import java.sql.Date;
import java.time.LocalDate;


@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    private String name;
    private String description;
    private Double amount;
    private Date date;
    private String type = "Debit";

    @ManyToOne
    private CreditCard creditCard;

    @ManyToOne
    private Merchant merchant;


    public Transaction() {
    }

    public Transaction(String name, String description, double amount, CreditCard creditCard, Merchant merchant) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.creditCard = creditCard;
        this.merchant = merchant;
        this.date = Date.valueOf(LocalDate.now());
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long id) {
        this.transactionId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

