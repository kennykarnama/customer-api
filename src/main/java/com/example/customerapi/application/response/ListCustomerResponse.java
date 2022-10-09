package com.example.customerapi.application.response;

import java.util.List;

import com.example.customerapi.domain.Customer;

import lombok.Data;

@Data
public class ListCustomerResponse {

    private final List<Customer> customers;
    
    private final Long numberOfItems;
    
    private final int numberOfPages;

    public ListCustomerResponse(final List<Customer> customers, final Long numberOfItems, final int numberOfPages) {
        this.customers = customers;
        this.numberOfItems = numberOfItems;
        this.numberOfPages = numberOfPages;
    }
}
