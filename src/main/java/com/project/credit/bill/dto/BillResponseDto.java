package com.project.credit.bill.dto;

import com.project.credit.bill.entity.Bill;
import com.project.credit.transaction.dto.TransactionResponseDto;


import java.util.List;


public class BillResponseDto {
    private long billId;
    private String cardNumber;
    private Double amount;
    private String billGeneratedDate;
    private String dueDate;
    private boolean isPaid;
    private List<TransactionResponseDto> transactions;

    public BillResponseDto() {
    }

    public BillResponseDto(long billId, String cardNumber, Double amount, String billGeneratedDate, String dueDate, boolean isPaid, List<TransactionResponseDto> transactions) {
        this.billId = billId;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.billGeneratedDate = billGeneratedDate;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
        this.transactions = transactions;
    }

    public BillResponseDto(Bill bill) {
        this.billId = bill.getBillId();
        this.cardNumber = bill.getCardNumber();
        this.amount = bill.getAmount();
        this.billGeneratedDate = bill.getBillGeneratedDate().toString();
        this.dueDate = bill.getDueDate().toString();
        this.isPaid = bill.isPaid();
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBillGeneratedDate() {
        return billGeneratedDate;
    }

    public void setBillGeneratedDate(String billGeneratedDate) {
        this.billGeneratedDate = billGeneratedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public List<TransactionResponseDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponseDto> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "BillResponseDto{" + ", billId=" + billId + ", cardNumber='" + cardNumber + '\'' + ", amount=" + amount + ", billGeneratedDate='" + billGeneratedDate + '\'' + ", dueDate='" + dueDate + '\'' + ", isPaid=" + isPaid + ", transactionResponseDtos=" + transactions + '}';
    }
}
