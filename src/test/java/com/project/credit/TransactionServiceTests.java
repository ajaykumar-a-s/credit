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
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.service.TransactionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

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
    TransactionDto transactionDto1 = null;
    TransactionDto transactionDto2 = null;

    @BeforeEach
    public void init() throws CustomerException, MerchantException, CreditCardRequestException, CardException {
        customerService.saveCustomer(customer);
        merchantService.saveMerchant(merchant);
        creditCardRequest = creditCardService.requestCard(customer.getCustomerId());
        creditCard = creditCardService.validateCreditCardRequest(creditCardRequest.getCreditCardRequestId());
        transactionDto1 = new TransactionDto(customer.getCreditCard().getCardNumber(), "John Doe", "12/25", customer.getCreditCard().getCvv(), "1234567890123456", "Amazon", "Test Transaction", 100.0);
        transactionDto2 = new TransactionDto(customer.getCreditCard().getCardNumber(), "John Doe", "12/25", customer.getCreditCard().getCvv(), "1234567890123456", "Amazon", "Test Transaction", 200000.0);

    }

    @AfterEach
    public void cleanUp() throws CustomerException, CardException, MerchantException {
        creditCardService.deleteCreditCardByCardNumber(creditCard.getCardNumber());
        customerService.deleteCustomerById(customer.getCustomerId());
        merchantService.deleteMerchantById(merchant.getMerchantId());

    }

    @Test
    public void testTransferAmount() {
        try {
            Transaction transaction = transactionService.transferAmount(transactionDto1);
            transactionService.deleteTransactionById(transaction.getTransactionId());
        } catch (TransactionException | CardException | MerchantException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetTransactionById() {
        try {
            Transaction transaction = transactionService.transferAmount(transactionDto1);
            transactionService.getTransactionById(transaction.getTransactionId());
            transactionService.deleteTransactionById(transaction.getTransactionId());
        } catch (TransactionException | CardException | MerchantException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetAllTransactions() {
        try {
            Transaction transaction1 = transactionService.transferAmount(transactionDto1);
            Transaction transaction2 = transactionService.transferAmount(transactionDto2);
            transactionService.getAllTransactions();
            transactionService.deleteTransactionById(transaction1.getTransactionId());
            transactionService.deleteTransactionById(transaction2.getTransactionId());
        } catch (MerchantException | TransactionException | CardException e) {
            System.out.println(e.getMessage());
        }
    }

}
