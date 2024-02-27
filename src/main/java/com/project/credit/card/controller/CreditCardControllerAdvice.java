package com.project.credit.card.controller;

import com.project.credit.card.exception.CardException;
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
}


