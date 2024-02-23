package com.project.credit;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardType;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceTests {
    @Autowired
    private TransactionService transactionService;

    @Test
    void testAddNullTransaction() {
        Transaction transaction = null;
        Assertions.assertThrows(TransactionException.class, ()->transactionService.addTransaction(transaction));
    }
    @Test
    void testAddTransaction(){
        Transaction transaction = new Transaction("Test Transaction", "Test Description", 100.0);
        try {
            transaction = transactionService.addTransaction(transaction);
            Assertions.assertNotNull(transaction);
        } catch (TransactionException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testWithAmountGreaterThanLimit(){
        Transaction transaction = new Transaction("Test Transaction", "Test Description", 100000.0);
        CreditCard fromCreditCard = new CreditCard("1234567890123456", null, null, new CreditCardType("test", 1000000.0, null) );
        Assertions.assertThrows(TransactionException.class, ()->transactionService.addTransaction(transaction));
    }
// git test

}
