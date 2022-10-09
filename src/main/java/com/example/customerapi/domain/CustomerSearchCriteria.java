package com.example.customerapi.domain;

import lombok.Data;

@Data
public class CustomerSearchCriteria {
    private String name;
    private String address;
    public CustomerSearchCriteria() {}
}
