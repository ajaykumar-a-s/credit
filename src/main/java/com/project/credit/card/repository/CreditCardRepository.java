package com.project.credit.card.repository;

import com.project.credit.bill.entity.Bill;
import com.project.credit.card.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {


    CreditCard findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);
    List<Bill> findAllBillsByCardNumber(String cardNumber);
}
