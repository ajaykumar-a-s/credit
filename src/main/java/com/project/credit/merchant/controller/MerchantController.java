package com.project.credit.merchant.controller;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {
    @Autowired
    private MerchantService merchantService;
    @PostMapping("/merchant")
    public Merchant saveMerchant() {
        try {
            return merchantService.saveMerchant(null);
        } catch (com.project.credit.merchant.exception.MerchantException e) {
            throw new RuntimeException(e);
        }
    }


}
//git