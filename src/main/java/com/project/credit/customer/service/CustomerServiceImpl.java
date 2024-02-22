package com.project.credit.customer.service;

import com.project.credit.customer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }


    @Override
    public Customer getCustomerById(Long customerId) {
        return null;
    }
    @Override
    public Customer editCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer deleteCustomer (Long customerId)
    {
        return null;
    }

    public List<Customer> viewAllCustomer()
    {
        return null;
    }
}

