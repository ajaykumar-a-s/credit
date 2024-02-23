package com.project.credit.card.service;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardType;
import com.project.credit.card.exception.CardException;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;

import java.util.List;

public interface CreditCardService {
    public CreditCard findCreditCardByCardNumber(String cardNumber) throws CardException;
CreditCard requestCard(Long customerId) throws CustomerException;
List<Customer> getRequestedCardList() throws CardException;
CreditCard validateCustomer(Long customerId) throws CustomerException;
CreditCard generateCard(CreditCardType creditCardType) throws CardException;


}
