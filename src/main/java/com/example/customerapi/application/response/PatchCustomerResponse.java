package com.example.customerapi.application.response;

import java.util.List;

import com.example.customerapi.domain.Customer;

public class PatchCustomerResponse {
    
    private final List<Customer> customers;

    public PatchCustomerResponse(final List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

}
