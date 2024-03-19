package com.project.credit.customer.controller;

import com.project.credit.LoginDto;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/customer")
    public Customer saveCustomer(@RequestBody Customer customer) throws CustomerException
    {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") Long customerId) throws CustomerException
    {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("/customer")
    public Customer updateCustomer(@RequestBody Customer customer) throws  CustomerException
    {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public Customer deleteCustomerById(@PathVariable("id") Long customerId) throws  CustomerException
    {
        return customerService.deleteCustomerById(customerId);
    }

    @GetMapping("/customers")
    public List<Customer> viewAllCustomers() throws CustomerException
    {
        return customerService.viewAllCustomers();
    }
    @PostMapping("/customer-login")
    public Customer loginCustomer(@RequestBody LoginDto loginDto) throws CustomerException
    {
        return customerService.loginCustomer(loginDto);
    }
}

