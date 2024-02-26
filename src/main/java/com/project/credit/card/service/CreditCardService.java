package com.project.credit.card.service;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardType;
import com.project.credit.card.exception.CardException;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CreditCardService {

    public CreditCard findCreditCardByCardNumber(Long cardNumber) throws CardException;

    public CreditCard requestCard(Long customerId) throws CustomerException;

    public List<Customer> getRequestedCardList() throws CardException;

    public CreditCard validateCustomer(Long customerId) throws CustomerException, CardException;

    public CreditCard generateCard(CreditCardType creditCardType) throws CardException;

    public CreditCard updateCreditCard(CreditCard creditCard) throws CardException;


}
