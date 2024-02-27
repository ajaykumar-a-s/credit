package com.project.credit.card.repository;

import com.project.credit.card.entity.CreditCardRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRequestRepository extends JpaRepository<CreditCardRequest, Long>{
    CreditCardRequest findByCustomer_id(Long id);
}
