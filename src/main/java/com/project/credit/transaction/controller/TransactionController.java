package com.project.credit.transaction.controller;

import com.project.credit.card.exception.CardException;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.transaction.dto.TransactionRequestDto;
import com.project.credit.transaction.dto.TransactionResponseDto;
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
    public TransactionResponseDto transferAmount(@RequestBody TransactionRequestDto transactionRequestDto) throws CardException, MerchantException, TransactionException {
        return this.covertTransactionToTransactionResponseDto(transactionService.transferAmount(transactionRequestDto));
    }
    @GetMapping("transaction/{id}")
    public TransactionResponseDto getTransactionById(@PathVariable("id") Long id) throws TransactionException {
        return this.covertTransactionToTransactionResponseDto(transactionService.getTransactionById(id));
    }
    @GetMapping("transactions")
    public List<TransactionResponseDto> getAllTransactions() throws TransactionException {
        return transactionService.getAllTransactions().stream().map(this::covertTransactionToTransactionResponseDto).toList();
    }
    @GetMapping("transactions/{cardNumber}")
    public List<TransactionResponseDto> getAllTransactionsByCardNumber(@PathVariable("cardNumber") String cardNumber) throws CardException, TransactionException {
        return this.transactionService.getAllTransactionsByCardNumber(cardNumber).stream().map(this::covertTransactionToTransactionResponseDto).toList();
    }
    @GetMapping("transactions/{cardNumber}/{startDate}/{endDate}")
    public List<TransactionResponseDto> getAllTransactionsByCardNumberForParticularDuration(@PathVariable("cardNumber") String cardNumber, @PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate) throws CardException, DateException, TransactionException {
        return transactionService.getAllTransactionsByCardNumberForParticularDuration(cardNumber, startDate, endDate).stream().map(this::covertTransactionToTransactionResponseDto).toList();
    }
    public TransactionResponseDto covertTransactionToTransactionResponseDto(Transaction transaction) {
        return new TransactionResponseDto(transaction.getTransactionId(), transaction.getName(), transaction.getDescription(), transaction.getAmount(), transaction.getDate(), transaction.getType(), transaction.getCreditCard().getCardNumber(), transaction.getMerchant().getCardNumber());
    }

}
