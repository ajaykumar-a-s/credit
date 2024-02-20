package com.project.credit.bill.controller;

import com.project.credit.bill.entity.Bill;
import com.project.credit.bill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
    @Autowired
    private BillService billService;
    @PostMapping("/bill")
    public Bill saveBill() {
        return billService.saveBill(null);
    }
}
