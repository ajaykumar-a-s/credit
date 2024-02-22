package com.project.credit.bill.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.card.exception.CardException;

public interface BillService {
    Bill autoGenerateBillForMonth() throws BillException;
   Bill billPayment(String cardNumber) throws BillException, CardException;

}

