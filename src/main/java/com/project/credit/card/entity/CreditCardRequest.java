package com.project.credit.card.entity;

import com.project.credit.customer.entity.Customer;
import jakarta.persistence.*;

@Entity
public class CreditCardRequest {
    @Id
    @GeneratedValue
    private Long id;

    private Boolean approved = false;

    @ManyToOne
    private Customer customer;

    public CreditCardRequest() {
    }


    public CreditCardRequest(boolean approved) {
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer creditCard) {
        this.customer = creditCard;
    }
}
