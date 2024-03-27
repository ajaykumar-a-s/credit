package com.project.credit.transaction.controller;

import com.project.credit.card.exception.CardException;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.transaction.dto.TransactionRequestDto;
import com.project.credit.transaction.dto.TransactionResponseDto;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
@Validated
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/transfer-amount")
    public TransactionResponseDto transferAmount(@Valid @RequestBody TransactionRequestDto transactionRequestDto) throws CardException, MerchantException, TransactionException {
        return transactionService.covertTransactionToTransactionResponseDto(transactionService.transferAmount(transactionRequestDto));
    }
    @GetMapping("transaction/{id}")
    public TransactionResponseDto getTransactionById(@PathVariable("id") @NotNull Long id) throws TransactionException {
        return transactionService.covertTransactionToTransactionResponseDto(transactionService.getTransactionById(id));
    }
    @GetMapping("transactions")
    public List<TransactionResponseDto> getAllTransactions() throws TransactionException {
        return transactionService.getAllTransactions().stream().map(transactionService::covertTransactionToTransactionResponseDto).toList();
    }
    @GetMapping("transactions/{customerCreditCardNumber}")
    public List<TransactionResponseDto> getAllTransactionsByCustomerCreditCardNumber(@PathVariable("customerCreditCardNumber") @NotBlank String customerCreditCardNumber) throws CardException, TransactionException {
        return this.transactionService.getAllTransactionsByCustomerCreditCardNumber(customerCreditCardNumber).stream().map(transactionService::covertTransactionToTransactionResponseDto).toList();
    }
    @GetMapping("transactions/{customerCreditCardNumber}/{startDate}/{endDate}")
    public List<TransactionResponseDto> getAllTransactionsByCustomerCreditCardNumberForParticularDuration(@PathVariable("customerCreditCardNumber") @NotBlank String customerCreditCardNumber, @PathVariable("startDate") @NotNull Date startDate, @PathVariable("endDate") @NotNull Date endDate) throws CardException, DateException, TransactionException {
        return transactionService.getAllTransactionsByCustomerCreditCardNumberForParticularDuration(customerCreditCardNumber, startDate, endDate).stream().map(transactionService::covertTransactionToTransactionResponseDto).toList();
    }
}
