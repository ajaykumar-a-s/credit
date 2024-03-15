package com.project.credit.bill.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import com.project.credit.bill.repository.BillRepository;
import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.card.exception.CardException;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {


    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private BillRepository billRepository;

    private List<Transaction> transactionsOfMonth= new ArrayList<>();
    private CreditCard creditCard =null;
    private  Double amountToBePaid = 0.0;

    @Override
    public Bill autoGenerateBillForMonth(String cardNumber) throws BillException, TransactionException, DateException, CardException {

        LocalDate currentDate=LocalDate.now();
        LocalDate lastMonthDate = currentDate.minusMonths(1);
        Date firstDateOfMonth = Date.valueOf(lastMonthDate.withDayOfMonth(1));
        Date lastDateOfMonth = Date.valueOf(lastMonthDate.withDayOfMonth(lastMonthDate.lengthOfMonth()));
        Date dueDate = Date.valueOf(currentDate.withDayOfMonth(15));


        creditCard =creditCardService.findCreditCardByCardNumber(cardNumber);
        LocalDate cardCreatedDate = creditCard.getCardCreatedOn().toLocalDate();
        if(cardCreatedDate.getMonth().compareTo(currentDate.getMonth())==0)
        {
            throw new BillException("You have to use your Credit Card for atleast one month to generate bill.");
        }
       List<Bill> bills = creditCard.getBills();
        if(!bills.isEmpty())
            if (bills.get(bills.size() - 1).getBillGeneratedDate().toLocalDate().getMonth().compareTo(currentDate.getMonth()) == 0) {
                    return bills.get(bills.size() - 1);
        }

        transactionsOfMonth=  transactionService.getAllTransactionsByCustomerCreditCardNumberForParticularDuration(cardNumber,firstDateOfMonth,lastDateOfMonth);

         for (Transaction list :transactionsOfMonth) {
              amountToBePaid +=list.getAmount();
         }

        if(transactionsOfMonth==null) {
           throw new BillException("You didn't use your Credit Card, So no bill is genertated.");
              }
        Bill newBill = new Bill( cardNumber, transactionsOfMonth, amountToBePaid,Date.valueOf(currentDate), dueDate, false);
        creditCard.getBills().add(newBill);
        return billRepository.save(newBill);

    }




    @Override
    public Bill billPayment(String cardNumber) throws BillException, CardException, TransactionException {
    creditCard = creditCardService.findCreditCardByCardNumber(cardNumber);
        List<Bill> bills = creditCard.getBills();
        if(!bills.isEmpty()) {
            if (bills.get(bills.size() - 1).isPaid()) {
                throw new BillException("You have paid all the bills");
            }
            else {
                bills.get(bills.size() - 1).setPaid(true);
                creditCard.setCurrentLimit(creditCard.getCreditCardType().getCreditLimit());
            }
        }
        else {
            throw new BillException("No bill is generated for this month");
        }
        Transaction creditTransaction = new Transaction("Bill Payment","Credit Recharge",amountToBePaid, creditCard,null);
        creditTransaction.setTransactionType("Credit");
        transactionService.addTransaction(creditTransaction);
        return bills.get(bills.size()-1);
    }




}
