package com.project.credit;

import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionServiceTests {
    @Autowired
    private TransactionService transactionService;

    @Test
    public void testAddNullTransaction() {
        Transaction transaction = null;
        Assertions.assertThrows(TransactionException.class, ()->transactionService.addTransaction(transaction));
    }



}
