package com.project.credit.transaction.controller;

import com.project.credit.card.exception.CardException;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionControllerAdvice {
    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity transactionException(TransactionException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = DateException.class)
    public ResponseEntity dateException(DateException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = MerchantException.class)
    public ResponseEntity merchantException(MerchantException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CardException.class)
    public ResponseEntity cardException(CardException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

