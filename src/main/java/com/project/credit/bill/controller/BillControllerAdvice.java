package com.project.credit.bill.controller;

import com.project.credit.bill.exception.BillException;
import com.project.credit.card.exception.CardException;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST);

        // Get all validation errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        // Add errors to the response body
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST);

        // Get all validation errors
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        // Add errors to the response body
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    

}
