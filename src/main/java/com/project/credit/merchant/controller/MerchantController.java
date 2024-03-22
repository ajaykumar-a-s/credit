package com.project.credit.merchant.controller;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/merchant")
    public Merchant saveMerchant(@RequestBody Merchant merchant) throws MerchantException
    {
        return merchantService.saveMerchant(merchant);
    }

    @GetMapping("/merchant/{id}")
    public Merchant getMerchantById(@PathVariable("id") Long merchantId) throws MerchantException
    {
        return merchantService.getMerchantById(merchantId);
    }

    @PutMapping("/merchant/update")
    public Merchant updateMerchant(@RequestBody Merchant merchant) throws  MerchantException
    {
        return merchantService.updateMerchant(merchant);
    }


    @DeleteMapping("/merchant/{id}")
    public Merchant deleteMerchantById(@PathVariable("id") Long merchantId) throws  MerchantException
    {
        return merchantService.deleteMerchantById(merchantId);
    }

    @GetMapping("/merchants")
    public List<Merchant> viewAllMerchants() throws MerchantException
    {
        return merchantService.viewAllMerchants();
    }



}























































