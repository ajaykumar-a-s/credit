package com.project.credit.transaction.service;

import com.project.credit.card.exception.CardException;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;

import java.sql.Date;
import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction) throws TransactionException;

    Transaction getTransactionById(Long id) throws TransactionException;

    List<Transaction> getAllTransactions() throws TransactionException;

    List<Transaction> getAllTransactionsByCardId(String cardId) throws CardException, TransactionException;

    List<Transaction> getAllTransactionsByCustomerId(Long customerId) throws CustomerException, TransactionException;

    List<Transaction> getAllTransactionsByCardIdForParticularDuration(String cardId, Date startDate, Date endDate) throws CardException, DateException, TransactionException;
}
