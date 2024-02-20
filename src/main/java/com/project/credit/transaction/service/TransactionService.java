package com.project.credit.transaction.service;

import com.project.credit.transaction.entity.Transaction;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
}
