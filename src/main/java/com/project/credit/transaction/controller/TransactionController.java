package com.project.credit.transaction.controller;

import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/transaction")
    public Transaction saveTransaction() {
        return transactionService.saveTransaction(null);
    }
}
