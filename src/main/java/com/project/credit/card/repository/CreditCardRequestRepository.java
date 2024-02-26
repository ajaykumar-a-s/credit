package com.project.credit.card.repository;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRequestRepository extends JpaRepository<CreditCardRequest, Long>{
    List<CreditCardRequest> findByNameContaining(String name);

}
