package com.project.credit.merchant.service;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

































