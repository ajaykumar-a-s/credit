package com.project.credit.transaction.entity;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.customer.entity.Customer;
import com.project.credit.merchant.entity.Merchant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private double amount;
    @ManyToOne
    private CreditCard creditCard;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Merchant merchant;

    public Transaction() {
    }

    public Transaction(String name, String description, double amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
