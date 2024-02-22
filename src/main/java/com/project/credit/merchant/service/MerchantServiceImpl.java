package com.project.credit.merchant.service;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService{

   // private Map <Integer,Merchant> merchantMap = new HashMap<>();

   // private final MerchantRepository MerchantRepository;

   /* public MerchantServiceImpl(MerchantRepository merchantRepository) {
        super();
        this.MerchantRepository = merchantRepository;
    }*/

    @Override
    public List<Merchant> getAllMerchants() {
        return null;
    }

    @Override
    public Merchant saveMerchant(Merchant merchant) throws MerchantException {

       // this.merchantMap.put(merchant.getId(),merchant);
         //return this.productMap.get(product.getId());
       //return MerchantRepository.save(merchant);
        return null;
    }

    @Override
    public Merchant getMerchantById(Integer id) throws MerchantException {
        return null;
        //return MerchantRepository.findById(id).get();

    }
    @Override
    public Merchant editMerchant(Merchant merchant) throws MerchantException {
        //return MerchantRepository.save(merchant);
        return null;
    }

    @Override
    public Merchant deleteMerchant(Integer id) throws MerchantException
    {
       // MerchantRepository.deleteById(id);
        return null;
    }

    public List<Merchant> viewAllMerchant() throws MerchantException
    {

        return null;
    }






}
