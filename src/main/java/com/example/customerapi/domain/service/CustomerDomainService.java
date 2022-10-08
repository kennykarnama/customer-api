package com.example.customerapi.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.customerapi.domain.Customer;
import com.example.customerapi.domain.repository.CustomerRepository;

public class CustomerDomainService implements CustomerService {

    private final CustomerRepository customerRepo;

    public CustomerDomainService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    @Transactional
    public Optional<Customer> findById(int id) {
        return this.customerRepo.findById(id);
    }
    
}
