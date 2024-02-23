package com.project.credit.transaction.dto;

public class TransactionDto {
    private String fromCardNumber;
    private String fromCardHolderName;
    private String expiryDate;
    private String cvv;
    private String toCardNumber;
    private String name;
    private String description;
    private Double amount;

    public TransactionDto() {
    }

    public TransactionDto(String fromCardNumber, String fromCardHolderName, String expiryDate, String cvv, String toCardNumber, String name, String description, Double amount) {
        this.fromCardNumber = fromCardNumber;
        this.fromCardHolderName = fromCardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.toCardNumber = toCardNumber;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public String getFromCardNumber() {
        return fromCardNumber;
    }

    public void setFromCardNumber(String fromCardNumber) {
        this.fromCardNumber = fromCardNumber;
    }

    public String getFromCardHolderName() {
        return fromCardHolderName;
    }

    public void setFromCardHolderName(String fromCardHolderName) {
        this.fromCardHolderName = fromCardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getToCardNumber() {
        return toCardNumber;
    }

    public void setToCardNumber(String toCardNumber) {
        this.toCardNumber = toCardNumber;
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
}
