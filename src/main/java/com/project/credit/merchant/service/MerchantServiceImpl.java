package com.project.credit.merchant.service;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;
//    private Merchant merchant;


    @Override
    public Merchant saveMerchant(Merchant merchant) throws MerchantException {
        try {
            // Save the merchant using the repository
            return merchantRepository.save(merchant);
        } catch (Exception e) {
            // Handle any exceptions and throw a merchant CustomerException
            throw new MerchantException("Failed to save merchant: " + e.getMessage());
        }
    }

    @Override
    public Merchant getMerchantById(Long merchantId) throws MerchantException {
        try {
            // Find the merchant by ID
            Optional<Merchant> optionalMerchant = merchantRepository.findById(merchantId);

            // Check if merchant exists
            if (optionalMerchant.isPresent()) {
                return optionalMerchant.get(); // Return the customer if found
            } else {
                throw new MerchantException("Customer not found with ID: " + merchantId);
            }
        } catch (Exception e) {
            // Handle any exceptions and throw a custom MerchantException
            throw new MerchantException("Failed to get merchant by ID: " + e.getMessage());
        }

    }

    @Override
    public Merchant getMerchantByCardNumber(String cardNumber) throws MerchantException
    {
        return null;
    }

    @Override
    public Merchant updateMerchant(Merchant merchant) throws MerchantException {
        return null;
    }

    @Override
    public Merchant deleteMerchantById(Long merchantId) throws MerchantException {
        try {

            Merchant existingMerchant = merchantRepository.findById(merchantId)
                    .orElseThrow(() -> new MerchantException("Merchant not found with ID: " + merchantId));
            merchantRepository.deleteById(merchantId);
            return existingMerchant;


        } catch (Exception e) {

            throw new MerchantException("Failed to delete customer: " + e.getMessage());
        }
    }



    @Override
    public List<Merchant> viewAllMerchants() throws MerchantException {
        try {

            return merchantRepository.findAll();
        } catch (Exception e) {

            throw new MerchantException("Failed to retrieve merchants!!: " + e.getMessage());
        }
    }

}


















