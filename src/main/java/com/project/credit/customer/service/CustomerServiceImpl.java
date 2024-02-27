package com.project.credit.customer.service;

import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) throws CustomerException {
        if (customer == null) {
            throw new CustomerException("Customer cannot be null");
        }
        return customerRepository.save(customer);
    }


    @Override
    public Customer getCustomerById(Long customerId) throws CustomerException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer with id " + customerId + " not found"));

    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerException {
        Customer oldCustomer=null;
        try {
             oldCustomer = getCustomerById(customer.getCustomerId());
        } catch (CustomerException e) {
            throw e;
        }
        return customerRepository.save(customer);

    }


    @Override
    public Customer deleteCustomerById(Long customerId) throws CustomerException {
        Customer customer=null;
        try{
            customer=getCustomerById(customerId);
        } catch (CustomerException e) {
            throw e;
        }
        customerRepository.deleteById(customerId);
        return customer;
    }

    @Override
    public List<Customer> viewAllCustomers() throws CustomerException {
        List<Customer> customers =customerRepository.findAll();
        if (customers.isEmpty())
        {
            throw new CustomerException("No customers found");
        }
        return customers;
    }
}



