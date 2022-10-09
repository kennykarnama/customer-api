package com.example.customerapi.infrastructure.repository.postgresql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.customerapi.domain.Customer;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer custId;

    @Column(nullable = false)
    private String custName;

    @Column(nullable = false)
    private String custAddress;

    public CustomerEntity() {}

    public CustomerEntity(Customer c) {
        this.custName = c.getName();
        this.custAddress = c.getAddress();
    }

    public Customer toCustomer() {
        Customer c = new Customer(this.custId, this.custName, this.custAddress);
        return c;
    }
}
