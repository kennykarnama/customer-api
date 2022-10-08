package com.example.customerapi.application.request;

import javax.validation.constraints.NotNull;

import com.example.customerapi.domain.Customer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveCustomerRequest {
    @NotNull private Customer customer;

    @JsonCreator
    public SaveCustomerRequest(@JsonProperty("customer") final Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
