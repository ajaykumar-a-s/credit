package com.project.credit.customer.service;

import com.project.credit.LoginDto;
import com.project.credit.customer.entity.Customer;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        if (!customer.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            throw new CustomerException("Invalid email format");
        }
        String customerPassword = customer.getPassword();
        if (customerPassword.length() < 8 || customerPassword.equals(customerPassword.toLowerCase()) || customerPassword.equals(customerPassword.toUpperCase()) || !customerPassword.matches(".*\\d.*") || customerPassword.matches("[a-zA-Z0-9 ]*")) {
            throw new CustomerException("Password is in invalid format");
        }
        if (!customer.getPhone().matches("^[6-9]\\d{9}$")){
            throw new CustomerException("Phone number is invalid format");
        }
        if (customer.getDateOfBirth().compareTo(new Date(System.currentTimeMillis())) > 0){
            throw new CustomerException("DOB cannot be in future");
        }
        if (new Date(System.currentTimeMillis()).getYear() - customer.getDateOfBirth().getYear() < 18){
            throw new CustomerException("You should be greater than 18 years");
        }
        if (new Date(System.currentTimeMillis()).getYear() - customer.getDateOfBirth().getYear() > 100){
            throw new CustomerException("Age should be below 100");
        }
        if(customer.getAnnualIncome() < 0){
            throw new CustomerException("Annual income cannot be negative");
        }
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            throw new CustomerException("Customer with email " + customer.getEmail() + " already exists");
        }
        if (customerRepository.findByPhone(customer.getPhone()) != null) {
            throw new CustomerException("Customer with phone " + customer.getPhone() + " already exists");
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
        if (customer.getCustomerId() == null) {
            throw new CustomerException("Customer id cannot be null");
        }
        getCustomerById(customer.getCustomerId());
        return customerRepository.save(customer);

    }


    @Override
    public Customer deleteCustomerById(Long customerId) throws CustomerException {
        Customer customer = getCustomerById(customerId);
        if(customer.getCreditCard() != null){
            throw new CustomerException("Has credit card, cannot delete customer");
        }
        customerRepository.deleteById(customerId);
        return customer;
    }

    @Override
    public Customer loginCustomer(LoginDto loginDto) throws CustomerException {
        if (loginDto.getEmail() == null || loginDto.getEmail().isEmpty()){
            throw new CustomerException("Email cannot be null");
        }
        if(loginDto.getPassword() == null || loginDto.getPassword().isEmpty()){
            throw new CustomerException("Password cannot be null");
        }
        if(!loginDto.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
            throw new CustomerException("Invalid email format");
        }
        if (!loginDto.getPassword().matches(".*\\d.*") || loginDto.getPassword().length() < 8 || loginDto.getPassword().equals(loginDto.getPassword().toLowerCase()) || loginDto.getPassword().equals(loginDto.getPassword().toUpperCase()) || loginDto.getPassword().matches("[a-zA-Z0-9 ]*")){
            throw new CustomerException("Invalid password format");
        }
        Customer customer = customerRepository.findByEmail(loginDto.getEmail());
        if(customer == null){
            throw new CustomerException("Customer with email " + loginDto.getEmail() + " not found");
        }
        if (!customer.getPassword().equals(loginDto.getPassword())){
            throw new CustomerException("Invalid password");
        }
        return customer;
    }

    @Override
    public List<Customer> viewAllCustomers() throws CustomerException {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new CustomerException("No customers found");
        }
        return customers;
    }
}



