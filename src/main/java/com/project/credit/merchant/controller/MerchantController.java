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

    @PostMapping  ("/merchant")
    public ResponseEntity<Merchant> saveMerchant(@RequestBody Merchant merchant) {
        try {
            Merchant savedMerchant = merchantService.saveMerchant(merchant);
            return new ResponseEntity<>(savedMerchant, HttpStatus.CREATED);
        } catch (MerchantException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/merchant/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable("id") Long merchantId) {
        try {
            Merchant merchant = merchantService.getMerchantById(merchantId);
            return new ResponseEntity<>(merchant, HttpStatus.OK);
        } catch (MerchantException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/merchant")
    public ResponseEntity<Merchant> updateMerchant(@RequestBody Merchant merchant) {
        try {
            Merchant updatedMerchant = merchantService.updateMerchant(merchant);
            return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
        } catch (MerchantException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/merchant/{id}")
    public ResponseEntity<?> deleteMerchantById(@PathVariable("id") Long merchantId) {
        try {
            merchantService.deleteMerchantById(merchantId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MerchantException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping  ("/merchants")
    public ResponseEntity<List<Merchant>> viewAllMerchants() {
        try {
            List<Merchant> merchants = merchantService.viewAllMerchants();
            return new ResponseEntity<>(merchants, HttpStatus.OK);
        } catch (MerchantException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}









































