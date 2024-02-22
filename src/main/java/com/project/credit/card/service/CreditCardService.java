package com.project.credit.card.service;

import com.project.credit.card.entity.CreditCard;

public interface CreditCardService {
    public CreditCard findCreditCardByCardNumber(String cardNumber);
}
