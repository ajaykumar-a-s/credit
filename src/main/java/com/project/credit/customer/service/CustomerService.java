package com.project.credit.customer.service;

import com.project.credit.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);


    Customer getCustomerById(Long customerId);

    Customer editCustomer(Customer customer);

    Customer deleteCustomer(Long customerId);

    List<Customer> viewAllCustomer();
}
