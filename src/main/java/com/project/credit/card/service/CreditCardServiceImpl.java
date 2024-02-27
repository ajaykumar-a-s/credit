package com.project.credit.card.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.card.entity.CreditCardType;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.repository.CreditCardRepository;
import com.project.credit.card.repository.CreditCardRequestRepository;
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
    public CreditCardRequest requestCard(Long customerId) throws CustomerException {
        Customer customer = customerService.getCustomerById(customerId);
        CreditCardRequest creditCardRequest = new CreditCardRequest();
        creditCardRequest.setCustomer(customer);
        return creditCardRequestRepository.save(creditCardRequest);

    }

    @Override
    public List<CreditCardRequest> getRequestedCardList() throws CardException {

        return creditCardRequestRepository.findAll();


    }

    @Override
    public CreditCard validateCustomer(Long customerId) throws CustomerException, CardException {
        Customer customer = customerService.getCustomerById(customerId);

        Double income = customer.getAnnualIncome() / 12;

        if (income < 100000)
            throw new CustomerException("Not eligible");
        if (customer.getCreditCard() != null)
            throw new CustomerException("You already holds a credit card");


        return generateCard(customer);
    }


    @Override
    public CreditCard generateCard(Customer customer) throws CardException {
       CreditCardType creditCardType = null;
        Double income = customer.getAnnualIncome() / 12;
        switch ((int) (Math.ceil(income / 100000))) {
            case 1:
                creditCardType = new CreditCardType("BRONZE", 100000D, 10D);
                break;
            case 2:
                creditCardType = new CreditCardType("SILVER", 200000D, 8D);
                break;
            case 3:
                creditCardType = new CreditCardType("GOLD", 300000D, 7D);
                break;
            default:
                creditCardType = new CreditCardType("PLATINUM", 400000D, 5D);
                break;
        }
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(generateCardNumber());
        creditCard.setCvv(generateRandomCvv());
        creditCard.setValidUpto(getValidUptoDate());
        creditCard.setCreditCardType(creditCardType);
        creditCard.setCurrentLimit(creditCardType.getCreditLimit());
       //creditCardRequestRepository.findByCustomer_id(customer.getId()).setApproved(true);

        return creditCardRepository.save(creditCard);
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
        if (creditCard == null)
            throw new CardException("Credit Card not found in database");

        return creditCard;

    }

    public String generateCardNumber() throws CardException {


        String cardNumber;
        do {
            cardNumber = generateNextCardNumber();
        }
        while (creditCardRepository.existsByCardNumber(cardNumber));

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

        return creditCardRepository.findAll();


    }


    @Override
    public CreditCard updateCreditCard(CreditCard creditCard) throws CardException {
        if (findCreditCardByCardNumber(creditCard.getCardNumber()) == null)
            throw new CardException("No such credit card found in database");
        return creditCardRepository.save(creditCard);
    }

    @Override
    public List<Bill> getBillByCardNumber(String cardNumber) throws CardException, BillException {
        return null;
    }
}
