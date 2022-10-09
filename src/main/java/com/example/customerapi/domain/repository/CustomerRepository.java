package com.example.customerapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.example.customerapi.domain.Customer;
import com.example.customerapi.domain.CustomerSearchCriteria;
import com.example.customerapi.domain.PaginatedCustomer;
import com.example.customerapi.domain.Paging;

public interface CustomerRepository {
    
    List<Customer> saveAll(List<Customer> customers);
    
    Optional<Customer> findById(int id);
    
    void deleteById(int id);
    
    List<Customer> patchAll(List<Customer> customers);

    PaginatedCustomer findAllByNameAndAddressContains(String name, String address, Pageable pageable);

    PaginatedCustomer findAllByCriteria(CustomerSearchCriteria criteria, Paging paging);
}
