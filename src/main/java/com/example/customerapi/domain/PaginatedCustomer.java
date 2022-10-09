package com.example.customerapi.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginatedCustomer {

    private List<Customer> customers;
    
    private Long numberOfItems;
    
    private int numberOfPages;
}
