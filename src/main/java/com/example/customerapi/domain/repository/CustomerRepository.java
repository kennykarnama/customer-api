package com.example.customerapi.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.customerapi.domain.Customer;

public interface CustomerRepository {
    public List<Customer> saveAll(List<Customer> customers);
    public Optional<Customer> findById(int id);
}
