package com.project.credit;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.bill.service.BillService;
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
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class BillServiceTests {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private BillService billService;
    Customer customer = new Customer("John Doe", "johndoe@example.com", "Ninja@2002", "6234567890", "123 Main St", Date.valueOf("1990-01-01"), 2000000.0);
    Merchant merchant = new Merchant("Amazon", "amazon@merchant.com", "Ninja@2002", "9234567890", "123 Main St", Date.valueOf("1990-01-01"), 5000.0, "1234567890123456");
    CreditCardRequest creditCardRequest = null;
    CreditCard creditCard = null;
    TransactionDto transactionDto = null;

    @BeforeEach
    public void init() {
        try {
            customerService.saveCustomer(customer);
            merchantService.saveMerchant(merchant);
            creditCardRequest = creditCardService.requestCard(customer.getCustomerId());
            creditCard = creditCardService.validateCreditCardRequest(creditCardRequest.getCreditCardRequestId());
            creditCard.setCardCreatedOn(Date.valueOf(LocalDate.now().minusMonths(1)));
            transactionDto = new TransactionDto(customer.getCreditCard().getCardNumber(), "John Doe", "12/25", customer.getCreditCard().getCvv(), "1234567890123456", "Amazon", "Test Transaction", 100.0);
            for (int i=0;i<5;i++) {
                Transaction transaction = transactionService.transferAmount(transactionDto);
                transaction.setDate(Date.valueOf(LocalDate.now().minusMonths(1)));
                transactionService.addTransaction(transaction);

            }
        } catch (CustomerException | MerchantException | CreditCardRequestException | CardException|TransactionException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void cleanUp() {
        try {
            for (Transaction transaction : transactionService.getAllTransactions()) {
                transactionService.deleteTransactionById(transaction.getTransactionId());
            }
            creditCardService.deleteCreditCardByCardNumber(creditCard.getCardNumber());
            customerService.deleteCustomerById(customer.getCustomerId());
            merchantService.deleteMerchantById(merchant.getMerchantId());


        } catch (CardException | CustomerException|MerchantException|TransactionException e) {
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void NoTransactionBillGenerateTest() {
        creditCard.setCardCreatedOn(Date.valueOf(LocalDate.now()));
        Assertions.assertThrows(BillException.class, () -> billService.autoGenerateBillForMonth(creditCard.getCardNumber()));
    }
    @Test
    public void BillAllReadyPaid(){
        Assertions.assertThrows(BillException.class, () -> billService.billPayment(creditCard.getCardNumber()));
    }

    @Test
    public void BillGenerateTest(){
        try {
            Assertions.assertEquals(Bill.class, billService.autoGenerateBillForMonth(creditCard.getCardNumber()).getClass());
        } catch (BillException | TransactionException|DateException|CardException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void BillPaymentTest() {
        try {

            billService.autoGenerateBillForMonth(creditCard.getCardNumber());
            Assertions.assertEquals(Bill.class, billService.billPayment(creditCard.getCardNumber()).getClass());
        } catch (BillException | TransactionException | CardException|DateException e) {
            throw new RuntimeException(e);
        }
    }
}
