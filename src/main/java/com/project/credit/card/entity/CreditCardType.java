package com.project.credit.card.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CreditCardType implements Serializable {

    private String cardType;
    private Double creditLimit;
    private Double interestRate;

    public CreditCardType() {
    }

    public CreditCardType(String cardType, Double creditLimit, Double interestRate) {
        this.cardType = cardType;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}
