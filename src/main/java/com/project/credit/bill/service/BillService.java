package com.project.credit.bill.service;

import com.project.credit.bill.entity.Bill;

import java.time.LocalDate;

public interface BillService {
    Bill autoGenerateBillForMonth();
    Boolean billPayment(Long billId);

}

