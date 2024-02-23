package com.project.credit.transaction.service;

import com.project.credit.card.exception.CardException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.transaction.dto.TransactionDto;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private CreditCardService creditCardService;

    @Override
    public Transaction transferAmount(TransactionDto transactionDto) throws TransactionException, CardException, MerchantException {
        return null;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws TransactionException {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long id) throws TransactionException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() throws TransactionException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCardId(String cardId) throws CardException, TransactionException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCustomerId(Long customerId) throws CustomerException, TransactionException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCardIdForParticularDuration(String cardNumber, Date startDate, Date endDate) throws CardException, DateException, TransactionException {
        return null;
    }
}
