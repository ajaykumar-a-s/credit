package com.project.credit.card.controller;

import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.service.CreditCardRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardRequestController {
    @Autowired
    private CreditCardRequestService creditCardRequestService;

    @PostMapping("/request-credit-card")
    public CreditCardRequest requestCreditCard() {
        return creditCardRequestService.requestCreditCard();
    }
}
