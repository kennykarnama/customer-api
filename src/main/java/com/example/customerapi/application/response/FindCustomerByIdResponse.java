package com.example.customerapi.application.response;

import com.example.customerapi.domain.Customer;

public class FindCustomerByIdResponse {
    private final Customer customer;

    public FindCustomerByIdResponse(final Customer c) {
        this.customer = c;
    }

    public Customer getCustomer() {
        return customer;
    }
}
