package com.project.credit.card.service;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.exception.CardException;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService{
    @Override
    public CreditCard findCreditCardByCardNumber(String cardNumber) throws CardException {
        return null;
    }
}
