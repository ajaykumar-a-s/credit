package com.project.credit.card.controller;

import com.project.credit.card.exception.CardException;
import com.project.credit.card.exception.CreditCardRequestException;
import com.project.credit.customer.exception.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CreditCardControllerAdvice {
    @ExceptionHandler(value = CardException.class)
    public ResponseEntity handleCardException(CardException e)
    {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CreditCardRequestException.class)
    public ResponseEntity handleCreditCardRequestException(CreditCardRequestException e)
    {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = CustomerException.class)
    public ResponseEntity handleCustomerException(CustomerException e)
    {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


