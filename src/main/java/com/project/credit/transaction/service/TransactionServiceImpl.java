package com.project.credit.transaction.service;

import com.project.credit.transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Override
    public Transaction addTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCardId(String cardId) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCardIdForParticularDuration(String cardId, Date startDate, Date endDate) {
        return null;
    }
}
