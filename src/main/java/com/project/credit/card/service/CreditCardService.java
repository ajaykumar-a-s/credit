package com.project.credit.card.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.exception.CardException;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;

import java.util.Date;
import java.util.List;

public interface CreditCardService {

    public CreditCard findCreditCardByCardNumber(String cardNumber) throws CardException;

    public CreditCardRequest requestCard(Long customerId) throws CustomerException;

    public List<CreditCardRequest> getRequestedCardList() throws CardException;

    public CreditCard validateCustomer(Long customerId) throws CustomerException, CardException;

    public CreditCard generateCard(Customer customer) throws CardException;

    public CreditCard updateCreditCard(CreditCard creditCard) throws CardException;
    public  String  generateCardNumber() throws  CardException;
    public Integer generateRandomCvv();
    public Date getValidUptoDate();
    public List<Bill> getBillByCardNumber(String cardNumber) throws CardException, BillException;


}
