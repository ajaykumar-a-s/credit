package com.project.credit.card.repository;

import com.project.credit.card.entity.CreditCardRequest;
import com.project.credit.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRequestRepository extends JpaRepository<Customer, Long>{
   // List<Account> findByNameContaining(String name);
}
