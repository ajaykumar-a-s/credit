package com.project.credit.transaction.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransactionDto {
    private String fromCardNumber;
    private String fromCardHolderName;
    private java.sql.Date expiryDate;
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
        this.setExpiryDate(expiryDate);
        this.cvv = cvv;
        this.toCardNumber = toCardNumber;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }


    public void setExpiryDate(String expiryDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        java.util.Date date = null;
        try {
            date = sdf.parse(expiryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // set day to the last of the month
        this.expiryDate = new java.sql.Date(cal.getTimeInMillis());
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