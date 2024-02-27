package com.project.credit.merchant.controller;

import com.project.credit.merchant.exception.MerchantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MerchantControllerAdvice {
    @ExceptionHandler(value = MerchantException.class)
    public ResponseEntity handleMerchantException(MerchantException e)
    {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}








