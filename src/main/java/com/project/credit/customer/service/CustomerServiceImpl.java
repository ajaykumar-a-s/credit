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
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new CustomerException("Customer name cannot be null");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new CustomerException("Customer email cannot be null");
        }
        if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
            throw new CustomerException("Customer password cannot be null");
        }
        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            throw new CustomerException("Customer phone cannot be null");
        }
        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            throw new CustomerException("Customer address cannot be null");
        }
        if (customer.getDateOfBirth() == null) {
            throw new CustomerException("Customer date of birth cannot be null");
        }
        if (customer.getAnnualIncome() == null) {
            throw new CustomerException("Customer annual income cannot be null");
        }
        return customerRepository.save(customer);
    }


    @Override
    public Customer getCustomerById(Long customerId) throws CustomerException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer with id " + customerId + " not found"));

    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerException {
        if (customer == null) {
            throw new CustomerException("Customer cannot be null");
        }
        getCustomerById(customer.getCustomerId());
        return customerRepository.save(customer);

    }


    @Override
    public Customer deleteCustomerById(Long customerId) throws CustomerException {
        Customer customer = getCustomerById(customerId);
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



