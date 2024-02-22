package com.project.credit.customer.controller;

import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/customer")
    public Customer saveCustomer() {
        try {
            return customerService.saveCustomer(null);
        } catch (com.project.credit.customer.exception.CustomerException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public Customer getCustomerById(@PathVariable("id")Long customerId)
    {
        try {
            return customerService.getCustomerById(customerId);
        } catch (com.project.credit.customer.exception.CustomerException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public String deleteCustomerById(@PathVariable("id")Long customerId)
    {
        try {
            customerService.deleteCustomerById(customerId);
        } catch (com.project.credit.customer.exception.CustomerException e) {
            throw new RuntimeException(e);
        }
        return "Deleted Successfully";
    }


    @GetMapping
    public List<Customer> viewAllCustomers() {
        try {
            return CustomerService.viewAllCustomers();
        } catch (com.project.credit.customer.exception.CustomerException e) {
            throw new RuntimeException(e);
        }
    }

}
//github


