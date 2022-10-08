package com.example.customerapi.domain.service;

import java.util.List;
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
    public List<Customer> saveAll(List<Customer> customers) {
        return this.customerRepo.saveAll(customers);
    }

    @Override
    @Transactional
    public Optional<Customer> findById(int id) {
        return this.customerRepo.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        this.customerRepo.deleteById(id)                                   ;
    }

    @Override
    @Transactional
    public List<Customer> updatePartials(List<Customer> customers) {
        List<Customer> updated = this.customerRepo.patchAll(customers);
        return updated;
    }
}
