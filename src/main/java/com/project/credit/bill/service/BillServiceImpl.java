package com.project.credit.bill.service;

import com.project.credit.bill.entity.Bill;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class BillServiceImpl implements BillService {


    @Override
    public Bill autoGenerateBillForMonth() {
        LocalDate currentDate=LocalDate.now();
        if(currentDate.compareTo(currentDate.withDayOfMonth(currentDate.lengthOfMonth()))==0){
            LocalDate firstDateofMonth = currentDate.withDayOfMonth(1);
            LocalDate lastDateofMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
        }

        return null;
    }

    @Override
    public Boolean billPayment(Long billId) {
        return null;
    }
}
