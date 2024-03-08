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
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.TransactionException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

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
    public void testCardRequestWithValidData() {
        try {
            CreditCardRequest creditCardRequest1 = creditCardService.requestCard(customer.getCustomerId());
            Assertions.assertEquals(CreditCardRequest.class, creditCardRequest1.getClass());
            creditCardService.deleteCreditCardRequestById(creditCardRequest.getCreditCardRequestId());
        } catch (CustomerException | CreditCardRequestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testCardRequestWithInValidData() {
        Assertions.assertThrows(CreditCardRequestException.class, () -> creditCardService.getCreditCardRequestById(0L));
    }


    @Test
    public void testCardWithValidData() {
        try {
            CreditCard creditCard1 = creditCardService.validateCreditCardRequest(creditCardRequest.getCreditCardRequestId());
            Assertions.assertEquals(CreditCard.class, creditCard1.getClass());
            creditCardService.deleteCreditCardByCardNumber(creditCard.getCardNumber());
        } catch (CustomerException | CardException | CreditCardRequestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFindCreditCardByCardNumber() throws CardException {
        CreditCard foundCard = creditCardService.findCreditCardByCardNumber(creditCard.getCardNumber());
        Assertions.assertNotNull(foundCard);
        Assertions.assertEquals(creditCard.getCardNumber(), foundCard.getCardNumber());
    }

    @Test
    public void testUpdateCreditCard() throws CardException {
        creditCard.setCurrentLimit(60000.0);
        CreditCard updatedCard = creditCardService.updateCreditCard(creditCard);
        Assertions.assertNotNull(updatedCard);
        Assertions.assertEquals(creditCard.getCurrentLimit(), updatedCard.getCurrentLimit());
    }

    @Test
    public void testUpdateNonExistentCreditCard() {
        CreditCard nonExistentCard = new CreditCard();
        nonExistentCard.setCardNumber("9999999999999999");
        Assertions.assertThrows(CardException.class, () -> creditCardService.updateCreditCard(nonExistentCard));
    }

    @Test
    public void testDeleteCreditCardByCardNumber() throws CardException, CustomerException {
        CreditCard deletedCard = creditCardService.deleteCreditCardByCardNumber(creditCard.getCardNumber());
        Assertions.assertNotNull(deletedCard);
        Assertions.assertThrows(CardException.class, () -> creditCardService.findCreditCardByCardNumber(deletedCard.getCardNumber()));
    }

    @Test
    public void testDeleteNonExistentCreditCardByCardNumber() {
        Assertions.assertThrows(CardException.class, () -> creditCardService.deleteCreditCardByCardNumber("9999999999999999"));
    }

    @Test
    public void testRequestCardWithNonExistentCustomer() {
        Assertions.assertThrows(CustomerException.class, () -> creditCardService.requestCard(9999999999999999L));
    }

    @Test
    public void testGetRequestedCardList() throws CreditCardRequestException {
        List<CreditCardRequest> requests = creditCardService.getRequestedCardList();
        Assertions.assertFalse(requests.isEmpty());
    }

    @Test
    public void testValidateCreditCardRequestWithExistingRequest() throws CustomerException, CreditCardRequestException, CardException {

        Customer newCustomer = new Customer("Jane Doe", "janedoe@example.com", "Ninja@2003", "7234567890", "456 Main St", Date.valueOf("1991-01-01"), 3000000.0);
        customerService.saveCustomer(newCustomer);
        CreditCardRequest request = creditCardService.requestCard(newCustomer.getCustomerId());
        CreditCard card = creditCardService.validateCreditCardRequest(request.getCreditCardRequestId());
        Assertions.assertNotNull(card);
        Assertions.assertEquals(newCustomer.getCustomerId(), card.getCustomer().getCustomerId());
        customerService.deleteCustomerById(newCustomer.getCustomerId());
    }

    @Test
    public void testValidateCreditCardRequestWithNonExistentRequest() {
        Assertions.assertThrows(CreditCardRequestException.class, () -> creditCardService.validateCreditCardRequest(9999999999999999L));
    }

    @Test
    public void testGetCardListWithExistingCards() throws CardException {
        List<CreditCard> cards = creditCardService.getCardList();
        Assertions.assertFalse(cards.isEmpty());
    }

    @Test
    public void testGetCardListWithNoCards() throws CardException, CustomerException {
        // Assuming that the deleteCreditCardByCardNumber method deletes all cards in the database
        creditCardService.deleteCreditCardByCardNumber(creditCard.getCardNumber());
        Assertions.assertThrows(CardException.class, () -> creditCardService.getCardList());
    }

    @Test
    public void testUpdateCreditCardWithValidData() throws CardException {
        creditCard.setCurrentLimit(70000.0);
        CreditCard updatedCard = creditCardService.updateCreditCard(creditCard);
        Assertions.assertNotNull(updatedCard);
        Assertions.assertEquals(creditCard.getCurrentLimit(), updatedCard.getCurrentLimit());
    }

    @Test
    public void testDeleteCreditCardByCardNumberWithValidData() throws CardException, CustomerException {
        CreditCard deletedCard = creditCardService.deleteCreditCardByCardNumber(creditCard.getCardNumber());
        Assertions.assertNotNull(deletedCard);
        Assertions.assertThrows(CardException.class, () -> creditCardService.findCreditCardByCardNumber(deletedCard.getCardNumber()));
    }

    @Test
    public void testDeleteCreditCardByCardNumberWithInvalidData() {
        Assertions.assertThrows(CardException.class, () -> creditCardService.deleteCreditCardByCardNumber("9999999999999999"));
    }

    @Test
    public void testGetRequestedCardListWithExistingRequests() throws CreditCardRequestException {
        List<CreditCardRequest> requests = creditCardService.getRequestedCardList();
        Assertions.assertFalse(requests.isEmpty());
    }


    @Test
    public void testFindCreditCardByNonExistentCardNumber() {
        Assertions.assertThrows(CardException.class, () -> creditCardService.findCreditCardByCardNumber("9999999999999999"));
    }

    @Test
    public void testGetBillByNonExistentCardNumber() {
        Assertions.assertThrows(CardException.class, () -> creditCardService.getBillByCardNumber("9999999999999999"));
    }

    @Test
    public void testDeleteCreditCardRequestByIdWithNonExistentRequest() {
        Assertions.assertThrows(CreditCardRequestException.class, () -> creditCardService.deleteCreditCardRequestById(9999999999999999L));
    }

    @Test
    public void testGetCreditCardRequestByIdWithNonExistentRequest() {
        Assertions.assertThrows(CreditCardRequestException.class, () -> creditCardService.getCreditCardRequestById(9999999999999999L));
    }

    
}

