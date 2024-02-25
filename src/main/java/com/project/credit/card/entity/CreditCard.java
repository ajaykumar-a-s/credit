package com.project.credit.card.entity;

import com.project.credit.bill.entity.Bill;
import com.project.credit.transaction.entity.Transaction;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue
    private Long id;
    private String cardNumber;
    private Date validUpto;
    private String cvv;

    private Double currentLimit;


    @Embedded
    private CreditCardType creditCardType;

    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany
    private List<Bill> bills = new ArrayList<>();

    public CreditCard() {
    }

    public CreditCard(String cardNumber, Date validUpto, String cvv, CreditCardType creditCardType) {
        this.cardNumber = cardNumber;
        this.validUpto = validUpto;
        this.cvv = cvv;
        this.creditCardType = creditCardType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getValidUpto() {
        return validUpto;
    }

    public void setValidUpto(Date validUpto) {
        this.validUpto = validUpto;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Double getCurrentLimit() {
        return currentLimit;
    }

    public void setCurrentLimit(Double currentLimit) {
        this.currentLimit = currentLimit;
    }

}
