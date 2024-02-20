package com.project.credit.customer.controller;

import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/customer")
    public Customer saveCustomer() {
        return customerService.saveCustomer(null);
    }
}
//github