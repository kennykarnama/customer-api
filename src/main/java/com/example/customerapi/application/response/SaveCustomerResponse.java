package com.example.customerapi.application.response;

import java.util.List;

import com.example.customerapi.domain.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveCustomerResponse {
    
    private final List<Customer> customers;
    
    public SaveCustomerResponse(@JsonProperty("customers") final List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
    
}
