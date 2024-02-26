package com.project.credit.card.repository;

import com.project.credit.card.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findTopByOrderByIdDesc();

    CreditCard findByCardNumber(String cardNumber);

    boolean existsByCardNumber(String cardNumber);
}
