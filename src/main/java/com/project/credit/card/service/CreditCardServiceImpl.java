package com.project.credit.card.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardType;
import com.project.credit.card.exception.CardException;
import org.springframework.stereotype.Service;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class CreditCardServiceImpl implements CreditCardService{
    @Autowired
    private CustomerService customerService;
    @Override
    public CreditCard findCreditCardByCardNumber(String cardNumber) throws CardException {
        return null;
    }

    @Override
    public CreditCard requestCard(Long customerId) throws CustomerException {
        return null;
    }

    @Override
    public List<Customer> getRequestedCardList() throws CardException {
        return null;
    }

    @Override
    public CreditCard validateCustomer(Long customerId) throws CustomerException {
        return null;
    }

    @Override
    public CreditCard generateCard(CreditCardType creditCardType) throws CardException {
        return null;
    }

    @Override
    public CreditCard updateCreditCard(CreditCard creditCard) throws CardException {
        return null;
    }

    @Override
    public List<Bill> getBillByCardNumber(String cardNumber) throws CardException, BillException {
        return null;
    }
}
