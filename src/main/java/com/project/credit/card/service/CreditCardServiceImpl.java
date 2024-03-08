package com.project.credit.card.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.entity.CreditCardType;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.exception.CreditCardRequestException;
import com.project.credit.card.repository.CreditCardRepository;
import com.project.credit.card.repository.CreditCardRequestRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CreditCardRequestRepository creditCardRequestRepository;


    @Override
    public CreditCardRequest requestCard(Long customerId) throws CustomerException, CreditCardRequestException {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer.getCreditCard() != null) {
            throw new CreditCardRequestException("Customer already has a credit card");
        }
        List<CreditCardRequest> creditCardRequests = creditCardRequestRepository.findAllByCustomer_CustomerId(customerId);
        for (CreditCardRequest creditCardRequest : creditCardRequests) {
            if (creditCardRequest.getStatus().equals("requested")) {
                throw new CreditCardRequestException("Customer already requested a card. Please wait for validation");
            }
        }
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setCustomer(customer);
        return creditCardRequestRepository.save(creditCardRequest);

    }

    @Override
    public List<CreditCardRequest> getRequestedCardList() throws CreditCardRequestException {


        List<CreditCardRequest> creditCardRequests = creditCardRequestRepository.findAllByStatus("requested");
        if (creditCardRequests.isEmpty()) {
            throw new CreditCardRequestException("No requests found");
        }
        return creditCardRequests;


    }

    @Override
    public CreditCard validateCreditCardRequest(Long creditCardRequestId) throws CustomerException, CardException, CreditCardRequestException {
        CreditCardRequest creditCardRequest = creditCardRequestRepository.findById(creditCardRequestId).orElse(null);
        if (creditCardRequest == null) {
            throw new CreditCardRequestException("No such request exists for the given id");
        }
        if (creditCardRequest.getStatus().equals("approved")) {
            throw new CreditCardRequestException("Request already approved");
        }
        Customer customer = creditCardRequest.getCustomer();

        Double annualIncome = customer.getAnnualIncome();

        if (annualIncome < 400000) {
            creditCardRequest.setStatus("rejected");
            creditCardRequestRepository.save(creditCardRequest);
            throw new CustomerException("Not eligible");
        }
        if (customer.getCreditCard() != null) {
            creditCardRequest.setStatus("rejected");
            creditCardRequestRepository.save(creditCardRequest);
            throw new CustomerException("You already holds a credit card");
        }
        return generateCard(creditCardRequest);
    }


    @Override
    public CreditCard generateCard(CreditCardRequest creditCardRequest) throws CustomerException {
        CreditCardType creditCardType = null;
        Double annualIncome = creditCardRequest.getCustomer().getAnnualIncome();
        if (annualIncome > 400000) {
            if (annualIncome < 600000) {
                creditCardType = new CreditCardType("BRONZE", 30000D, 10D);
            } else if (annualIncome < 800000) {
                creditCardType = new CreditCardType("SILVER", 50000D, 8D);
            } else if (annualIncome < 1200000) {
                creditCardType = new CreditCardType("GOLD", 60000D, 7D);
            } else {
                creditCardType = new CreditCardType("PLATINUM", 100000D, 5D);
            }
        }

        Customer customer = creditCardRequest.getCustomer();
        CreditCard creditCard = new CreditCard(generateCardNumber(), getValidUptoDate(), generateRandomCvv(), creditCardType.getCreditLimit(), customer, creditCardType);
        creditCardRepository.save(creditCard);
        customer.setCreditCard(creditCard);
        customerService.updateCustomer(customer);
        creditCardRequest.setStatus("approved");
        creditCardRequestRepository.save(creditCardRequest);
        return customer.getCreditCard();

    }

    public Date getValidUptoDate() {
        LocalDate validUptoLocalDate = LocalDate.now().plusYears(2); // Get the date 2 years from the current date
        return Date.valueOf(validUptoLocalDate);
    }

    public Integer generateRandomCvv() {
        return ThreadLocalRandom.current().nextInt(100, 1000); // Generate a random number between 100 and 999
    }

    @Override
    public CreditCard findCreditCardByCardNumber(String cardNumber) throws CardException {
        CreditCard creditCard = creditCardRepository.findByCardNumber(cardNumber);
        if (creditCard == null) throw new CardException("Credit Card not found in database");

        return creditCard;

    }

    public String generateCardNumber() {


        String cardNumber;
        do {
            cardNumber = generateNextCardNumber();
        } while (creditCardRepository.existsByCardNumber(cardNumber));

        return cardNumber;


    }

    public String generateNextCardNumber() {
        Random random = new Random();

        StringBuilder cardNumber = new StringBuilder("4"); // Starting with 4 for Visa cards
        for (int i = 0; i < 15; i++) { // 15 more digits to make the length 16
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    @Override
    public List<CreditCard> getCardList() throws CardException {
        List<CreditCard> creditCards = creditCardRepository.findAll();
        if (creditCards.isEmpty()) {
            throw new CardException("No credit cards found in database");
        }
        return creditCards;
    }


    @Override
    public CreditCard deleteCreditCardByCardNumber(String cardNumber) throws CardException, CustomerException {
        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new CardException("Card number cannot be null or empty");
        }
        CreditCard creditCard = findCreditCardByCardNumber(cardNumber);
        if (creditCard == null) {
            throw new CardException("Credit card not found in database");
        }
        creditCard.getCustomer().setCreditCard(null);
        customerService.updateCustomer(creditCard.getCustomer());
        creditCardRepository.delete(creditCard);
        return creditCard;
    }

    @Override
    public CreditCardRequest deleteCreditCardRequestById(Long creditCardRequestId) throws CreditCardRequestException {
        if (creditCardRequestId == null) {
            throw new CreditCardRequestException("Credit card request ID cannot be null");
        }
        CreditCardRequest creditCardRequest = getCreditCardRequestById(creditCardRequestId);
        creditCardRequestRepository.delete(creditCardRequest);
        return null;
    }
    @Override
    public CreditCardRequest getCreditCardRequestById(Long creditCardRequestId) throws CreditCardRequestException {
        CreditCardRequest creditCardRequest=creditCardRequestRepository.findById(creditCardRequestId).orElse(null);
        if(creditCardRequest==null)
            throw new CreditCardRequestException("No such request found");
        return creditCardRequest;
    }


    public CreditCard updateCreditCard(CreditCard creditCard) throws CardException {
        if (creditCard == null) {
            throw new CardException("Credit card cannot be null");
        }
        if (findCreditCardByCardNumber(creditCard.getCardNumber()) == null)
            throw new CardException("No such credit card found in database");
        return creditCardRepository.save(creditCard);
    }


    @Override
    public List<Bill> getBillByCardNumber(String cardNumber) throws CardException, BillException {

        findCreditCardByCardNumber(cardNumber);
        List<Bill> bills = creditCardRepository.findAllBillsByCardNumber(cardNumber);
        if (bills.isEmpty()) {
            throw new BillException("No bills found for the given card number");
        }
        return bills;
    }

}
