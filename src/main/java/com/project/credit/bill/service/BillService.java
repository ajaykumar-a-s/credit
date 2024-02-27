package com.project.credit.bill.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.card.exception.CardException;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;

import java.util.List;

public interface BillService {
    Bill autoGenerateBillForMonth(String cardNumber) throws BillException, TransactionException, DateException, CardException;
    Bill billPayment(String cardNumber) throws BillException, CardException, TransactionException;

}

