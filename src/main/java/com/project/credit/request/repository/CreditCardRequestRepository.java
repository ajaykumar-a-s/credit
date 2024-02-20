package com.project.credit.request.repository;

import com.project.credit.request.entity.CreditCardRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRequestRepository extends JpaRepository<CreditCardRequest, Long>{
}
