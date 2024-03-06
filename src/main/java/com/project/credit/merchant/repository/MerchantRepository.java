package com.project.credit.merchant.repository;

import com.project.credit.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Merchant findByCardNumber(String cardNumber);
    Merchant findByEmail(String email);
    Merchant findByPhone(String phone);
}
