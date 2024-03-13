package com.project.credit.transaction.dto;

import com.project.credit.transaction.exception.DateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransactionRequestDto {
    private String fromCardNumber;
    private String fromCardHolderName;
    private java.sql.Date expiryDate;
    private Integer cvv;
    private String toCardNumber;
    private String toCardHolderName;
    private String transactionName;
    private String description;
    private Double amount;

    public TransactionRequestDto() {
    }

    public TransactionRequestDto(String fromCardNumber, String fromCardHolderName, String expiryDate, Integer cvv, String toCardNumber, String toCardHolderName, String transactionName, String description, Double amount) throws DateException {
        this.fromCardNumber = fromCardNumber;
        this.fromCardHolderName = fromCardHolderName;
        this.toCardHolderName = toCardHolderName;
        this.setExpiryDate(expiryDate);
        this.cvv = cvv;
        this.toCardNumber = toCardNumber;
        this.transactionName = transactionName;
        this.description = description;
        this.amount = amount;
    }


    public void setExpiryDate(String expiryDate) throws DateException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        java.util.Date date = null;
        try {
            date = sdf.parse(expiryDate);
        } catch (ParseException e) {
            throw new DateException("Invalid date format. Please use MM/yy format.");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // set day to the last of the month
        this.expiryDate = new java.sql.Date(cal.getTimeInMillis());
    }
    public void setExpiryDate(java.sql.Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public java.sql.Date getExpiryDate() {
        return this.expiryDate;
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

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getToCardNumber() {
        return toCardNumber;
    }

    public void setToCardNumber(String toCardNumber) {
        this.toCardNumber = toCardNumber;
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

    public String getToCardHolderName() {
        return toCardHolderName;
    }

    public void setToCardHolderName(String toCardHolderName) {
        this.toCardHolderName = toCardHolderName;
    }
}