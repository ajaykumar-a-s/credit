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
    private CreditCardService creditCardService;

    private List<Transaction> transactionsOfMonth= new ArrayList<>();

    @Override
    public Bill autoGenerateBillForMonth(String cardNumber) throws BillException, TransactionException, DateException, CardException {

        LocalDate currentDate=LocalDate.now();
        LocalDate lastMonthDate = currentDate.minusMonths(1);
        Date firstDateOfMonth = Date.valueOf(lastMonthDate.withDayOfMonth(1));
        Date lastDateOfMonth = Date.valueOf(lastMonthDate.withDayOfMonth(currentDate.lengthOfMonth()));
        Date dueDate = Date.valueOf(currentDate.withDayOfMonth(15));

         transactionsOfMonth=  transactionService.getAllTransactionsByCardIdForParticularDuration(cardNumber,firstDateOfMonth,lastDateOfMonth);
        Double amountToBePaid = null;
         for (Transaction list :transactionsOfMonth) {
              amountToBePaid =+list.getAmount();
         }

        if(transactionsOfMonth!=null)
        return new Bill(transactionsOfMonth.get(0).getName(),null,transactionsOfMonth,amountToBePaid,dueDate,false);
        else
            return null;
    }
    private CreditCard creditCardByCardNumber =null;



    @Override
    public Bill billPayment(String cardNumber) throws BillException, CardException {
    creditCardByCardNumber= creditCardService.findCreditCardByCardNumber(cardNumber);
    for (Bill billList :creditCardByCardNumber.getBills()){
        if(billList.isPaid()==false){
            billList.setPaid(true);
            creditCardByCardNumber.setCurrentLimit(creditCardByCardNumber.getCreditCardType().getCreditLimit());
            return billList;
        }


    }
    return null;



    }


}
