package com.project.credit.card.controller;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.service.CustomerService;
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
        return creditCardService.requestCard(customerId);
    }
    @GetMapping("/requestlist")
    public List<CreditCardRequest> getRequestedCardList() throws CardException
    {
        return creditCardService.getRequestedCardList();
    }
    @PostMapping("/validate/{id}")
    public CreditCard validateCustomer(@PathVariable("id") Long customerId) throws CustomerException, CardException {
        return creditCardService.validateCustomer(customerId);
    }

   

}
