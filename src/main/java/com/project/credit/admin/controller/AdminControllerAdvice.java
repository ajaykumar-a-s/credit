package com.project.credit.admin.controller;

import com.project.credit.admin.exception.AdminException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminControllerAdvice {
    @ExceptionHandler(value = AdminException.class)
    public ResponseEntity handleAdminException(AdminException adminException) {
        return new ResponseEntity<>(adminException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
