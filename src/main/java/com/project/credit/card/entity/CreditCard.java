package com.project.credit.card.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.credit.bill.entity.Bill;
import com.project.credit.customer.entity.Customer;
import com.project.credit.transaction.entity.Transaction;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue
    private Long creditCardId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private String cardNumber;
    private Date validUpto;
    private Integer cvv;

    private Double currentLimit;
    private Date cardCreatedOn;

    @OneToOne
    @JsonIgnore
    private Customer customer;


    @Embedded
    private CreditCardType creditCardType;


    @OneToMany
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany
    private List<Bill> bills = new ArrayList<>();

    public CreditCard() {
    }

    public CreditCard(String cardNumber, Date validUpto, Integer cvv, Double currentLimit, Customer customer, CreditCardType creditCardType) {
        this.cardNumber = cardNumber;
        this.validUpto = validUpto;
        this.cvv = cvv;
        this.currentLimit = currentLimit;
        this.cardCreatedOn = Date.valueOf(LocalDate.now());
        this.customer = customer;
        this.creditCardType = creditCardType;
    }

    public Long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Long id) {
        this.creditCardId = id;
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

    public Integer getCvv() {

        return cvv;
    }

    public void setCvv(Integer cvv) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCardCreatedOn() {
        return cardCreatedOn;
    }

    public void setCardCreatedOn(Date cardCreatedOn) {
        this.cardCreatedOn = cardCreatedOn;
    }
}
