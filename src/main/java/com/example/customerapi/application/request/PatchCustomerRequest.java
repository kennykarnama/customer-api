package com.example.customerapi.application.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.customerapi.domain.Customer;

import lombok.Data;

@Data
public class PatchCustomerRequest {
    
    @NotNull
    @Min(1)
    private Integer id;

    private String name;
    
    private String address;

    public Customer toCustomer() {
        return new Customer(this.id, this.name, this.address);
    }
}
