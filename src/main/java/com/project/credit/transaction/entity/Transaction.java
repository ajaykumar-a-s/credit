package com.project.credit.transaction.entity;


import com.project.credit.card.entity.CreditCard;
import com.project.credit.merchant.entity.Merchant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Date;


@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double amount;
    private Date date;
    private String type;
    @ManyToOne
    private CreditCard creditCard;
    @ManyToOne
    private Merchant merchant;



    public Transaction() {
    }

    public Transaction(String name, String description, double amount, Date date, String type) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
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

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}

