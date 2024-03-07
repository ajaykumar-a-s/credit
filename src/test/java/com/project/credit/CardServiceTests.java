package com.project.credit;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.exception.CreditCardRequestException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.service.CustomerService;
import com.project.credit.merchant.exception.MerchantException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
@Transactional
public class CardServiceTests {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CreditCardService creditCardService;
    Customer customer = new Customer("John Doe", "johndoe@example.com", "Ninja@2002", "6234567890", "123 Main St", Date.valueOf("1990-01-01"), 2000000.0);
    CreditCardRequest creditCardRequest = null;
    CreditCard creditCard = null;
    @BeforeEach
    public void init() throws CustomerException, MerchantException, CreditCardRequestException, CardException {
        customerService.saveCustomer(customer);
        creditCardRequest = creditCardService.requestCard(customer.getCustomerId());
        creditCard = creditCardService.validateCreditCardRequest(creditCardRequest.getCreditCardRequestId());
    }
    @AfterEach
    public void cleanUp() throws CustomerException, CardException, MerchantException, CreditCardRequestException {

        customerService.deleteCustomerById(customer.getCustomerId());
        creditCardService.deleteCreditCardRequestById(creditCardRequest.getCreditCardRequestId());

    }
    @Test
    public void testCardWithValidData() {
        try {
            CreditCard creditCard1=creditCardService.validateCreditCardRequest(creditCardRequest.getCreditCardRequestId());
            Assertions.assertEquals(CreditCard.class,creditCard1.getClass());
            creditCardService.deleteCreditCardByCardNumber(creditCard.getCardNumber());
        }
        catch (CustomerException | CardException | CreditCardRequestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCardRequestWithValidData() {
        try {
            CreditCardRequest creditCardRequest1=creditCardService.requestCard(customer.getCustomerId());
            Assertions.assertEquals(CreditCardRequest.class,creditCardRequest1.getClass());
            creditCardService.deleteCreditCardRequestById(creditCardRequest.getCreditCardRequestId());
        }
        catch (CustomerException | CreditCardRequestException e) {
            System.out.println(e.getMessage());
        }
    }
    }

