package com.project.credit.transaction.service;

import com.project.credit.card.exception.CardException;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.transaction.dto.TransactionRequestDto;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;

import java.sql.Date;
import java.util.List;

public interface TransactionService {

    Transaction transferAmount(TransactionRequestDto transactionRequestDto) throws CardException, MerchantException, TransactionException;
    Transaction addTransaction(Transaction transaction) throws TransactionException;

    Transaction getTransactionById(Long id) throws TransactionException;

    List<Transaction> getAllTransactions() throws TransactionException;

    List<Transaction> getAllTransactionsByCardNumber(String cardNumber) throws CardException, TransactionException;

    List<Transaction> getAllTransactionsByCardNumberForParticularDuration(String cardNumber, Date startDate, Date endDate) throws CardException, DateException, TransactionException;
    Transaction deleteTransactionById(Long id) throws TransactionException, CardException;
}
