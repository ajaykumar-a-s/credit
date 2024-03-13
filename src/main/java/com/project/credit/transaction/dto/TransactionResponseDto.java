package com.project.credit.transaction.dto;

import java.sql.Date;

public class TransactionResponseDto {
    private Long transactionId;
    private String name;
    private String description;
    private Double amount;
    private Date date;
    private String type;
    private String creditCardNumber;
    private String merchantCardNumber;

    public TransactionResponseDto() {
    }

    public TransactionResponseDto(Long transactionId, String name, String description, Double amount, Date date, String type, String creditCardNumber, String merchantCardNumber) {
        this.transactionId = transactionId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.creditCardNumber = creditCardNumber;
        this.merchantCardNumber = merchantCardNumber;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getMerchantCardNumber() {
        return merchantCardNumber;
    }

    public void setMerchantCardNumber(String merchantCardNumber) {
        this.merchantCardNumber = merchantCardNumber;
    }
}
