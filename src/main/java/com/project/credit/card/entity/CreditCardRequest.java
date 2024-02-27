package com.project.credit.card.entity;

import com.project.credit.customer.entity.Customer;
import jakarta.persistence.*;

@Entity
public class CreditCardRequest {
    @Id
    @GeneratedValue
    private Long creditCardRequestId;

    private String status = "requested";
    @ManyToOne
    private Customer customer;

    public CreditCardRequest() {
    }

    public Long getCreditCardRequestId() {
        return creditCardRequestId;
    }

    public void setCreditCardRequestId(Long id) {
        this.creditCardRequestId = id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer creditCard) {
        this.customer = creditCard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
