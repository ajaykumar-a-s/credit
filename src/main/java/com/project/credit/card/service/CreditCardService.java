package com.project.credit.card.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.exception.CreditCardRequestException;
import com.project.credit.customer.exception.CustomerException;

import java.util.Date;
import java.util.List;

public interface CreditCardService {

    public CreditCard findCreditCardByCardNumber(String cardNumber) throws CardException;

    public CreditCardRequest requestCard(Long customerId) throws CustomerException, CreditCardRequestException;

    public List<CreditCardRequest> getRequestedCardList() throws CreditCardRequestException;

    public CreditCard validateCreditCardRequest(Long creditCardRequestId) throws CustomerException, CardException, CreditCardRequestException;

    public CreditCard generateCard(CreditCardRequest creditCardRequest) throws CardException, CustomerException;

    public CreditCard updateCreditCard(CreditCard creditCard) throws CardException;
    public  String  generateCardNumber() throws  CardException;
    public Integer generateRandomCvv();
    public Date getValidUptoDate();
    public List<Bill> getBillByCardNumber(String cardNumber) throws CardException, BillException;
    public List<CreditCard> getCardList() throws CardException;
    public CreditCard deleteCreditCardByCardNumber(String cardNumber) throws CardException, CustomerException;

}
