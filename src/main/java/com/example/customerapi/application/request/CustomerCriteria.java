package com.example.customerapi.application.request;

import lombok.Data;

@Data
public class CustomerCriteria {

    private String name;

    private String address;

    public CustomerCriteria() {
        this.name = "";
        this.address = "";
    }
    
}
