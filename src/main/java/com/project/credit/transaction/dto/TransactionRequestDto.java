package com.project.credit.transaction.dto;

import com.project.credit.transaction.exception.DateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransactionRequestDto {
    private String customerCreditCardNumber;
    private String customerName;
    private java.sql.Date expiryDate;
    private Integer cvv;
    private String merchantCardNumber;
    private String merchantName;
    private String transactionName;
    private String description;
    private Double amount;

    public TransactionRequestDto() {
    }

    public TransactionRequestDto(String customerCreditCardNumber, String customerName, String expiryDate, Integer cvv, String merchantCardNumber, String merchantName, String transactionName, String description, Double amount) throws DateException {
        this.customerCreditCardNumber = customerCreditCardNumber;
        this.customerName = customerName;

        this.setExpiryDate(expiryDate);
        this.cvv = cvv;
        this.merchantCardNumber = merchantCardNumber;
        this.merchantName = merchantName;
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

    public String getCustomerCreditCardNumber() {
        return customerCreditCardNumber;
    }

    public void setCustomerCreditCardNumber(String customerCreditCardNumber) {
        this.customerCreditCardNumber = customerCreditCardNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getMerchantCardNumber() {
        return merchantCardNumber;
    }

    public void setMerchantCardNumber(String merchantCardNumber) {
        this.merchantCardNumber = merchantCardNumber;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}