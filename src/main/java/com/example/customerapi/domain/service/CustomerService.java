package com.example.customerapi.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.customerapi.domain.Customer;

public interface CustomerService {
    List<Customer> saveAll(List<Customer> customers);

    Optional<Customer> findById(int id);
}
