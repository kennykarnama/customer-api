package com.example.customerapi.domain.service;

import java.util.Optional;

import com.example.customerapi.domain.Customer;

public interface CustomerService {
    Customer save(Customer customer);

    Optional<Customer> findById(int id);
}
