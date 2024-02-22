package com.project.credit.customer.service;

import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer saveCustomer(Customer customer) throws CustomerException
    {
        //return customerRepository.save(customer);
        return null;
    }


    @Override
    public Customer getCustomerById(Long customerId) throws CustomerException {
        //return customerRepository.findById(customerId).get();
        return null;
    }
    /*@Override
    public Customer editCustomer(Customer customer) {
        return null;
    }*/


    @Override
    public void deleteCustomerById(Long customerId) throws CustomerException
    {
        //customerRepository.deleteById(customerId);
        return null;
    }

    @Override
    public List<Customer> viewAllCustomers() throws CustomerException
    {
        //return (List<Customer>)
                //customerRepository.findAll();
        return null;
    }
}



