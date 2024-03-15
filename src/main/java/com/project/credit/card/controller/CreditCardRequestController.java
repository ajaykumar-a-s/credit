package com.project.credit.card.controller;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.exception.CreditCardRequestException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.customer.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreditCardRequestController {
    @Autowired
    private CreditCardService creditCardService;
    @PostMapping("/request/{customerId}")
    public CreditCardRequest requestCard(@PathVariable("customerId") Long customerId) throws CustomerException, CreditCardRequestException {

            return creditCardService.requestCard(customerId);

    }
    @GetMapping("/requestlist")
    public List<CreditCardRequest> getRequestedCardList() throws CreditCardRequestException {

            return creditCardService.getRequestedCardList();

    }
    @PostMapping("/validate/{creditCardRequestId}")
    public CreditCard validateCustomer(@PathVariable("creditCardRequestId") Long creditCardRequestId) throws CustomerException, CardException, CreditCardRequestException {

            return creditCardService.validateCreditCardRequest(creditCardRequestId);

    }
    @GetMapping("/cardlist")
    public List<CreditCard> getCardList() throws CardException
    {
        return creditCardService.getCardList();
    }

   

}
