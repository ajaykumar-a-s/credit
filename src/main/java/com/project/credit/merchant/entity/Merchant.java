package com.project.credit.merchant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Merchant {
    @Id
    @GeneratedValue
    private Long merchantId;
    private String name;
    @JsonIgnore
    private Double balance;
    @Column(unique = true)
    private String cardNumber;

    public Merchant() {
    }

    public Merchant(String name, String cardNumber) {
        this.name = name;
        this.balance = 0.0;
        this.cardNumber = cardNumber;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long id) {
        this.merchantId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}
