package com.project.credit.merchant.service;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;

import java.util.List;

public interface MerchantService {
 List<Merchant> getAllMerchants();

 // List<Merchant> getAllMerchants();
    Merchant saveMerchant(Merchant merchant) throws MerchantException;
   // Merchant getMerchantById (Long id);

    Merchant getMerchantById(Integer id) throws MerchantException;

    Merchant getMerchantByCardNumber(String cardNumber) throws MerchantException;

    Merchant updateMerchant(Merchant merchant) throws MerchantException;

    Merchant deleteMerchant(Integer id) throws MerchantException;

    List<Merchant> viewAllMerchant() throws MerchantException;

}
