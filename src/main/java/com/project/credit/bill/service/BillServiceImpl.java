package com.project.credit.bill.service;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.exception.BillException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BillServiceImpl implements BillService {


    @Override
    public Bill autoGenerateBillForMonth() throws BillException {
        LocalDate currentDate=LocalDate.now();
        if(currentDate.compareTo(currentDate.withDayOfMonth(currentDate.lengthOfMonth()))==0){
            LocalDate firstDateofMonth = currentDate.withDayOfMonth(1);
            LocalDate lastDateofMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
        }

        return null;
    }

    @Override
    public Bill billPayment(String cardNumber) throws BillException {
        return null;
    }


}
