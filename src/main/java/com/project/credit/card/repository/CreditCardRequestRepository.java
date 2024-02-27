package com.project.credit.card.repository;

import com.project.credit.card.entity.CreditCardRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRequestRepository extends JpaRepository<CreditCardRequest, Long>{
    List<CreditCardRequest> findAllByCustomer_CustomerId(Long id);
    List<CreditCardRequest> findAllByStatus(String status);
}
