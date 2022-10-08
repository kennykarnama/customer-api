package com.example.customerapi.application.response;

import com.example.customerapi.domain.Customer;

public class SaveCustomerResponse {
    
    private final Customer customer;
    
    public SaveCustomerResponse(final Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    
}
