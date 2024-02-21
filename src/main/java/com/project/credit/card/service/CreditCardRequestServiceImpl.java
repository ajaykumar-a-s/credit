package com.project.credit.card.service;

import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.repository.CreditCardRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardRequestServiceImpl implements CreditCardRequestService{
    @Autowired
    private CreditCardRequestRepository creditCardRequestRepository;
    @Override
    public CreditCardRequest requestCreditCard() {
        return creditCardRequestRepository.save(null);
    }
}
