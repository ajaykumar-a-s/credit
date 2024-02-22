package com.project.credit.card.entity;

import com.project.credit.customer.entity.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CreditCardRequest {
    @Id
    @GeneratedValue
    private Long id;

    private boolean approved;

    private Long customerId;

    public CreditCardRequest() {
    }

    public CreditCardRequest(boolean approved, Long customerId) {
        this.approved = approved;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
