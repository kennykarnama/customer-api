package com.example.customerapi.domain.repository;

import java.util.Optional;

import com.example.customerapi.domain.Customer;

public interface CustomerRepository {
    public Customer save(Customer customer);
    public Optional<Customer> findById(int id);
}
