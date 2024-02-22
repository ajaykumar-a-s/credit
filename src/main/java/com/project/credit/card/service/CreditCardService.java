package com.project.credit.card.service;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.exception.CardException;

public interface CreditCardService {
    public CreditCard findCreditCardByCardNumber(String cardNumber) throws CardException;
}
