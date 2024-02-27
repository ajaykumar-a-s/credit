package com.project.credit.transaction.controller;

import com.project.credit.card.exception.CardException;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.transaction.dto.TransactionDto;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/transfer-amount")
    public Transaction transferAmount(@RequestBody TransactionDto transactionDto) throws CardException, MerchantException, TransactionException {
        return transactionService.transferAmount(transactionDto);
    }
    @GetMapping("transaction/{id}")
    public Transaction getTransactionById(@PathVariable("id") Long id) throws TransactionException {
        return transactionService.getTransactionById(id);
    }
    @GetMapping("transactions")
    public List<Transaction> getAllTransactions() throws TransactionException {
        return transactionService.getAllTransactions();
    }
    @GetMapping("transactions/{cardNumber}")
    public List<Transaction> getAllTransactionsByCardNumber(@PathVariable("cardNumber") String cardNumber) throws CardException, TransactionException {
        return transactionService.getAllTransactionsByCardNumber(cardNumber);
    }
    @GetMapping("transactions/{cardNumber}/{startDate}/{endDate}")
    public List<Transaction> getAllTransactionsByCardNumberForParticularDuration(@PathVariable("cardNumber") String cardNumber, @PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate) throws CardException, DateException, TransactionException {
        return transactionService.getAllTransactionsByCardNumberForParticularDuration(cardNumber, startDate, endDate);
    }

}
