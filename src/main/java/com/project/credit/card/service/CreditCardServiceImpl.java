package com.project.credit.card.service;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardType;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.repository.CreditCardRepository;
import com.project.credit.card.repository.CreditCardRequestRepository;
import com.project.credit.customer.service.CustomerServiceImpl;
import org.springframework.stereotype.Service;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class CreditCardServiceImpl implements CreditCardService{
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CreditCardRequestRepository creditCardRequestRepository;
@Autowired
private CreditCardType creditCardType;
@Autowired
private CreditCard creditCard;
    @Override
    public CreditCard requestCard(Long customerId) throws CustomerException {
        //Customer customer=customerServiceImpl.getCustomerById(customerId);
        //creditCardRequestRepository.save(customer);
       // return customer;
        return null;
    }

    @Override
    public List<Customer> getRequestedCardList() throws CardException {

//return creditCardRequestRepository.findAll();
        return null;

    }

    @Override
   public CreditCard validateCustomer(Long customerId) throws CustomerException, CardException {
//        Customer customer=customerServiceImpl.getCustomerById(customerId);
//        if(customer.getCreditCard()==null)
//        {
//            Double income=customer.getAnnualIncome()/12;
//            String type="";
//            switch ((int)(Math.ceil(income / 100000)))
//            {
//                case 1:
//                    type = "BRONZE";
//                    break;
//                case 2:
//                    type = "SILVER";
//                    break;
//                case 3:
//                    type = "GOLD";
//                    break;
//                default:
//                    type = "PLATINUM";
//                    break;
//            }
//           creditCardType.setCardType(type);
//            generateCard(creditCardType);
//        }
//        else {
//            throw new CustomerException("Not eligible");
//        }


        return null;
    }

    @Override
    public CreditCard generateCard(CreditCardType creditCardType) throws CardException {

        return null;
    }
    @Override
    public CreditCard findCreditCardByCardNumber(String cardNumber) throws CardException {
        //return creditCardRepository.findById(cardNumber).get();
        return null;

    }


    @Override
    public CreditCard updateCreditCard(CreditCard creditCard) throws CardException {
        return null;
    }
}
