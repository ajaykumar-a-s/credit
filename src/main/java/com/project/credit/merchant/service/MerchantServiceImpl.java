package com.project.credit.merchant.service;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;


    @Override
    public Merchant saveMerchant(Merchant merchant) throws MerchantException{
        if (merchant == null) {
            throw new MerchantException("Merchant cannot be null");
        }
        if (merchant.getBalance() == null) {
            throw new MerchantException("Balance cannot be null");
        }
        if (merchant.getName() == null || merchant.getName().isEmpty()) {
            throw new MerchantException("Merchant name cannot be empty");
        }
        if (merchant.getCardNumber() == null || merchant.getCardNumber().isEmpty()) {
            throw new MerchantException("Card number cannot be empty");
        }
        if (merchant.getCardNumber().length() != 16) {
            throw new MerchantException("Card number should be 16 digits");
        }
        if (!merchant.getCardNumber().matches("[0-9]+")) {
            throw new MerchantException("Card number should contain only digits");
        }
        if (merchant.getEmail() == null || merchant.getEmail().isEmpty()) {
            throw new MerchantException("Email cannot be empty");
        }
        if (!merchant.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            throw new MerchantException("Invalid email");
        }
        if (merchant.getPhone() == null || merchant.getPhone().isEmpty()) {
            throw new MerchantException("Phone number cannot be empty");
        }
        if (merchant.getPhone().length() != 10) {
            throw new MerchantException("Phone number should be 10 digits");
        }
        if (!merchant.getPhone().matches("[0-9]+")) {
            throw new MerchantException("Phone number should contain only digits");
        }
        if (merchant.getAddress() == null || merchant.getAddress().isEmpty()) {
            throw new MerchantException("Address cannot be empty");
        }
        if (merchant.getPassword() == null || merchant.getPassword().isEmpty()) {
            throw new MerchantException("Password cannot be empty");
        }
        if (merchant.getDateOfBirth() == null) {
            throw new MerchantException("Date of birth cannot be empty");
        }
        if (merchant.getDateOfBirth().compareTo(new Date(System.currentTimeMillis())) > 0) {
            throw new MerchantException("Date of birth cannot be in future");
        }
        if (new Date(System.currentTimeMillis()).getYear() - merchant.getDateOfBirth().getYear() < 18) {
            throw new MerchantException("Age should be greater than 18");
        }
        if (new Date(System.currentTimeMillis()).getYear() - merchant.getDateOfBirth().getYear() > 100) {
            throw new MerchantException("Age should be less than 100");
        }
        if (merchantRepository.findByEmail(merchant.getEmail()) != null) {
            throw new MerchantException("Merchant with email " + merchant.getEmail() + " already exists");
        }
        if (merchantRepository.findByPhone(merchant.getPhone()) != null) {
            throw new MerchantException("Merchant with phone number " + merchant.getPhone() + " already exists");
        }
        if (merchantRepository.findByCardNumber(merchant.getCardNumber()) != null) {
            throw new MerchantException("Merchant with card number " + merchant.getCardNumber() + " already exists");
        }


        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant getMerchantById(Long merchantId) throws MerchantException {
        return merchantRepository.findById(merchantId).orElseThrow(() -> new MerchantException("Merchant with id " + merchantId + " not found"));

    }

    @Override
    public Merchant updateMerchant(Merchant merchant) throws MerchantException {
        if (merchant == null) {
            throw new MerchantException("Merchant cannot be null");
        }
        if (merchant.getMerchantId() == null) {
            throw new MerchantException("Merchant id cannot be null");
        }
        getMerchantById(merchant.getMerchantId());
        return merchantRepository.save(merchant);

    }

    @Override
    public Merchant deleteMerchantById(Long merchantId) throws MerchantException {
        Merchant merchant=getMerchantById(merchantId);
        merchantRepository.deleteById(merchantId);
        return merchant;
    }

    @Override
    public List<Merchant> viewAllMerchants() throws MerchantException {
        List<Merchant> merchants =merchantRepository.findAll();
        if (merchants.isEmpty())
        {
            throw new MerchantException("No Merchants found");
        }
        return merchants;
    }



    @Override
    public Merchant getMerchantByCardNumber(String cardNumber) throws MerchantException
    {
        Merchant merchant =  merchantRepository.findByCardNumber(cardNumber);
        if (merchant == null) {
            throw new MerchantException("Merchant with card number " + cardNumber + " not found");
        }
        return merchant;
    }



}

































