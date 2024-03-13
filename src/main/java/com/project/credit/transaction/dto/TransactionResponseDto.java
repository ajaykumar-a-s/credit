package com.project.credit.transaction.dto;

import java.sql.Date;

public class TransactionResponseDto {
    private Long transactionId;
    private String transactionName;
    private String description;
    private Double amount;
    private Date date;
    private String transactionType;
    private String creditCardNumber;
    private String merchantCardNumber;

    public TransactionResponseDto() {
    }

    public TransactionResponseDto(Long transactionId, String transactionName, String description, Double amount, Date date, String transactionType, String creditCardNumber, String merchantCardNumber) {
        this.transactionId = transactionId;
        this.transactionName = transactionName;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
        this.creditCardNumber = creditCardNumber;
        this.merchantCardNumber = merchantCardNumber;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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
