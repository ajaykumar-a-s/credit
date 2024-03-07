package com.project.credit;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.exception.CreditCardRequestException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.service.CustomerService;
import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.service.MerchantService;
import com.project.credit.transaction.dto.TransactionDto;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.service.TransactionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class TransactionServiceTests {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private TransactionService transactionService;
    Customer customer = new Customer("John Doe", "johndoe@example.com", "Ninja@2002", "6234567890", "123 Main St", Date.valueOf("1990-01-01"), 2000000.0);
    Merchant merchant = new Merchant("Amazon", "amazon@merchant.com", "Ninja@2002", "9234567890", "123 Main St", Date.valueOf("1990-01-01"), 5000.0, "1234567890123456");
    CreditCardRequest creditCardRequest = null;
    CreditCard creditCard = null;
    TransactionDto transactionDto = null;

    @BeforeEach
    public void init() throws CustomerException, MerchantException, CreditCardRequestException, CardException {
        customerService.saveCustomer(customer);
        merchantService.saveMerchant(merchant);
        creditCardRequest = creditCardService.requestCard(customer.getCustomerId());
        creditCard = creditCardService.validateCreditCardRequest(creditCardRequest.getCreditCardRequestId());
        transactionDto = new TransactionDto(customer.getCreditCard().getCardNumber(), "John Doe", "12/25", customer.getCreditCard().getCvv(), "1234567890123456", "Amazon", "Test Transaction", 100.0);

    }

    @AfterEach
    public void cleanUp() throws CustomerException, CardException, MerchantException {
        creditCardService.deleteCreditCardByCardNumber(creditCard.getCardNumber());
        customerService.deleteCustomerById(customer.getCustomerId());
        merchantService.deleteMerchantById(merchant.getMerchantId());

    }

    @Test
    void testTransferAmount() {
        try {
            Transaction transaction = transactionService.transferAmount(transactionDto);
            Assertions.assertEquals(Transaction.class, transaction.getClass());
            transactionService.deleteTransactionById(transaction.getTransactionId());
        } catch (TransactionException | CardException | MerchantException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void testTransferAmountWithNullTransactionDto() {
        TransactionDto transactionDto = this.transactionDto;
        this.transactionDto = null;
        Assertions.assertThrows(TransactionException.class, () -> transactionService.transferAmount(this.transactionDto));
        this.transactionDto = transactionDto;
    }
    @Test
    void testTransferAmountWithNullFromCardNumber() {
        String fromCardNumber = transactionDto.getFromCardNumber();
        transactionDto.setFromCardNumber(null);
        Assertions.assertThrows(TransactionException.class, () -> transactionService.transferAmount(transactionDto));
        transactionDto.setFromCardNumber(fromCardNumber);
    }

    @Test
    void testGetTransactionById() {
        try {
            Transaction transaction = transactionService.transferAmount(transactionDto);
            Assertions.assertEquals(transaction, transactionService.getTransactionById(transaction.getTransactionId()));
            transactionService.deleteTransactionById(transaction.getTransactionId());
        } catch (TransactionException | CardException | MerchantException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void testGetTransactionByIdWithInvalidId() {
        Assertions.assertThrows(TransactionException.class, () -> transactionService.getTransactionById(0L));
    }

    @Test
    void testGetAllTransactions() {
        try {
            Transaction transaction1 = transactionService.transferAmount(transactionDto);
            Transaction transaction2 = transactionService.transferAmount(transactionDto);
            Assertions.assertEquals(new ArrayList<>(Arrays.asList(transaction1, transaction2)), transactionService.getAllTransactions());
            transactionService.deleteTransactionById(transaction1.getTransactionId());
            transactionService.deleteTransactionById(transaction2.getTransactionId());
        } catch (MerchantException | TransactionException | CardException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void testGetAllTransactionsWithNoTransactions() {
        Assertions.assertThrows(TransactionException.class, () -> transactionService.getAllTransactions());
    }
    @Test
    void testGetAllTransactionsByCardNumber() {
        try {
            Transaction transaction1 = transactionService.transferAmount(transactionDto);
            Transaction transaction2 = transactionService.transferAmount(transactionDto);
            List<Transaction> transactions = transactionService.getAllTransactionsByCardNumber(transactionDto.getFromCardNumber());
            Assertions.assertEquals(new ArrayList<>(Arrays.asList(transaction1, transaction2)), transactions);
            transactionService.deleteTransactionById(transaction1.getTransactionId());
            transactionService.deleteTransactionById(transaction2.getTransactionId());
        } catch (MerchantException | TransactionException | CardException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void testGetAllTransactionsByCardNumberWithNoTransactions() {
        Assertions.assertThrows(TransactionException.class, () -> transactionService.getAllTransactionsByCardNumber(customer.getCreditCard().getCardNumber()));
    }
    @Test
    void testGetAllTransactionsByCardNumberForParticularDuration() {
        try {
            Transaction transaction1 = transactionService.transferAmount(transactionDto);
            Transaction transaction2 = transactionService.transferAmount(transactionDto);
            List<Transaction> transactions = transactionService.getAllTransactionsByCardNumberForParticularDuration(transactionDto.getFromCardNumber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
            Assertions.assertEquals(new ArrayList<>(Arrays.asList(transaction1, transaction2)), transactions);
            transactionService.deleteTransactionById(transaction1.getTransactionId());
            transactionService.deleteTransactionById(transaction2.getTransactionId());
        } catch (MerchantException | TransactionException | CardException | DateException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void testGetAllTransactionsByCardNumberForParticularDurationWithNoTransactions() {
        Assertions.assertThrows(TransactionException.class, () -> transactionService.getAllTransactionsByCardNumberForParticularDuration(customer.getCreditCard().getCardNumber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
    }
    @Test
    void testGetAllTransactionsByCardNumberForParticularDurationWithNullDates() {
        Assertions.assertThrows(DateException.class, () -> transactionService.getAllTransactionsByCardNumberForParticularDuration(customer.getCreditCard().getCardNumber(), null, null));
    }
    @Test
    void testGetAllTransactionsByCardNumberForParticularDurationWithInvalidDates() {
        Assertions.assertThrows(DateException.class, () -> transactionService.getAllTransactionsByCardNumberForParticularDuration(customer.getCreditCard().getCardNumber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() - 100000)));
    }
    @Test
    void testDeleteTransactionById() {
        try {
            Transaction transaction = transactionService.transferAmount(transactionDto);
            Assertions.assertEquals(transaction, transactionService.deleteTransactionById(transaction.getTransactionId()));
        } catch (TransactionException | CardException | MerchantException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void testDeleteTransactionByIdWithInvalidId() {
        Assertions.assertThrows(TransactionException.class, () -> transactionService.deleteTransactionById(0L));
    }

}
