package com.project.credit.transaction.service;

import com.project.credit.transaction.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);

    Transaction getTransactionById(Long id);

    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionsByCardId(String cardId);

    List<Transaction> getAllTransactionsByCustomerId(Long customerId);

    List<Transaction> getAllTransactionsByCardIdForParticularDuration(String cardId, java.sql.Date startDate, java.sql.Date endDate);
}
