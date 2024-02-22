package com.project.credit.merchant.service;

import com.project.credit.merchant.entity.Merchant;

public interface MerchantService {
   // List<Merchant> getAllMerchants();
    Merchant saveMerchant(Merchant merchant);
    Merchant getMerchantById (Long id);

    Merchant editMerchant(Merchant merchant);

    Merchant deleteMerchant(Long id);

    Merchant viewAllMerchant(Merchant merchant);

}
