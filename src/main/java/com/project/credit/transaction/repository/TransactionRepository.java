package com.project.credit.transaction.repository;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCreditCard(CreditCard creditCard);
    List<Transaction> findAllByDateBetween(java.sql.Date startDate, java.sql.Date endDate);
}
