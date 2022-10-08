package com.example.customerapi.application.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.customerapi.domain.Customer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatchCustomerRequest {
    
    @NotNull private final List<Customer> customers;

    @JsonCreator
    public PatchCustomerRequest(@JsonProperty("customers") final List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
