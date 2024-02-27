package com.project.credit.merchant.service;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;

import java.util.List;

public interface MerchantService {

    Merchant deleteMerchantById(Long merchantId) throws MerchantException;
    List<Merchant> viewAllMerchants() throws MerchantException;

    Merchant saveMerchant(Merchant merchant) throws MerchantException;

    Merchant getMerchantById(Long merchantId) throws MerchantException;


    Merchant getMerchantByCardNumber(String cardNumber) throws MerchantException;

    Merchant updateMerchant(Merchant merchant) throws MerchantException;



}










