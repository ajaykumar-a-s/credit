package com.project.credit.bill.controller;

import com.project.credit.bill.exception.BillException;
import com.project.credit.card.exception.CardException;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BillControllerAdvice {
    @ExceptionHandler(value = BillException.class)
    public ResponseEntity handleBillException(BillException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity handleTransactionException(TransactionException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = DateException.class)
    public ResponseEntity handleDateException(DateException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CardException.class)
    public ResponseEntity handleCardException(CardException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    

}
