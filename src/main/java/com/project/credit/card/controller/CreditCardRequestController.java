package com.project.credit.card.controller;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.customer.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditCardRequestController {
    @Autowired
    private CreditCardService creditCardService;
    @PostMapping("/request/{id}")
    public CreditCardRequest requestCard(@PathVariable("id") Long customerId) throws CustomerException
    {
        try {
            return creditCardService.requestCard(customerId);
        } catch (com.project.credit.card.exception.CreditCardRequestException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/requestlist")
    public List<CreditCardRequest> getRequestedCardList() throws CardException
    {
        try {
            return creditCardService.getRequestedCardList();
        } catch (com.project.credit.card.exception.CreditCardRequestException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/validate/{id}")
    public CreditCard validateCustomer(@PathVariable("id") Long customerId) throws CustomerException, CardException {
        try {
            return creditCardService.validateCreditCardRequest(customerId);
        } catch (com.project.credit.card.exception.CreditCardRequestException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/cardlist")
    public List<CreditCard> getCardList() throws CardException
    {
        return creditCardService.getCardList();
    }

   

}
