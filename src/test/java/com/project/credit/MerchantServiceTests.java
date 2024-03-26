package com.project.credit;

import com.project.credit.merchant.service.MerchantService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
//import com.project.credit.transaction.exception.TransactionException;
import org.junit.jupiter.api.Assertions;
import java.sql.Date;
import java.time.LocalDate;

@Nested
@SpringBootTest
class MerchantServiceTests {
    @Autowired
    private MerchantService merchantService;
    Merchant merchant= new Merchant("John Doe", "1234567890876543");
    @Test
    void testSaveNullMerchant(){
        Assertions.assertThrows(MerchantException.class, () -> {
            merchantService.saveMerchant(null);
        });
    }
    @Test
    void testSaveNullMerchantName(){
        String merchantName = merchant.getName();
        merchant.setName(null);
        Assertions.assertThrows(MerchantException.class, () -> {
            merchantService.saveMerchant(merchant);
        });
        merchant.setName(merchantName);
    }
    @Test
    void testSaveValidMerchant(){
        try {
            Assertions.assertEquals(merchant, merchantService.saveMerchant(merchant));
            merchantService.deleteMerchantById(merchant.getMerchantId());
        } catch (MerchantException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    void testUpdateMerchantName() {
        String newMerchantName = "Jane Smith";
        try {
            Merchant savedMerchant = merchantService.saveMerchant(merchant);
            savedMerchant.setName(newMerchantName);
            Merchant updatedMerchant = merchantService.updateMerchant(savedMerchant);

            Assertions.assertEquals(newMerchantName, updatedMerchant.getName());

            // Clean up
            merchantService.deleteMerchantById(updatedMerchant.getMerchantId());
        } catch (MerchantException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testDeleteMerchant() {

        try {
            Merchant savedMerchant = merchantService.saveMerchant(merchant);
            merchantService.deleteMerchantById(savedMerchant.getMerchantId());

            Assertions.assertThrows(MerchantException.class, () -> {
                merchantService.getMerchantById(savedMerchant.getMerchantId());
            });
        }

        catch (MerchantException e) {
            System.out.println(e.getMessage());
        }
    }


}









