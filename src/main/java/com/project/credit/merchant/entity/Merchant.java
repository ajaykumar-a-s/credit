package com.project.credit.merchant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Merchant {
    @Id
    @GeneratedValue
    private Long merchantId;
    private String name;
    @Column(unique = true)
    private String email;

    private String password;
    @Column(unique = true)
    private String phone;
    private String address;
    private Date dateOfBirth;

    private Double balance;
    @Column(unique = true)
    private String cardNumber;

    public Merchant() {
    }

    public Merchant(String name, String email, String password, String phone, String address, Date dateOfBirth, String cardNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
