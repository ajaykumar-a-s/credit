package com.project.credit.merchant.service;

import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService{

    List<Merchant> getAllMerchants() {
        return null;
    }

  /*public MerchantServiceImpl(MerchantRepository merchantRepositoryRepository) {
      super();
      this.MerchantRepository = merchantRepository;
  }*/

    @Override
    public Merchant saveMerchant(Merchant merchant) {
       // return MerchantRepository.save(merchant);
        return null;
    }

    @Override
    public Merchant getMerchantById(Long id) {

        //return MerchantRepository.findById(id).get();
        return null;
    }
    @Override
    public Merchant editMerchant(Merchant merchant) {
        return null;
    }

    @Override
    public Merchant deleteMerchant(Long id)
    {
        return null;
    }

    @Override
    public Merchant viewAllMerchant(Merchant merchant)
    {
        return null;
    }






}
