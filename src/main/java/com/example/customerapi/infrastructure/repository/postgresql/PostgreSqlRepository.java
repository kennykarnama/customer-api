package com.example.customerapi.infrastructure.repository.postgresql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.customerapi.domain.Customer;
import com.example.customerapi.domain.repository.CustomerRepository;

@Component
@Primary
public class PostgreSqlRepository implements CustomerRepository {

    private final SpringDataRepository repo;

    @Autowired
    public PostgreSqlRepository(final SpringDataRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Customer> saveAll(List<Customer> customers) {
        List<CustomerEntity> savedData = new ArrayList<>();
        customers.forEach((c)->{
            savedData.add(new CustomerEntity(c));
        });
        this.repo.saveAll(savedData);
        List<Customer> savedCustomers = new ArrayList<>();
        savedData.forEach((s)->{
            savedCustomers.add(s.toCustomer());
        });
        return savedCustomers;
    }

    @Override
    public Optional<Customer> findById(int id) {
       Optional<CustomerEntity> customerData = this.repo.findById(id);
       
       if (customerData.isPresent()) {
        return Optional.of(customerData.get().toCustomer());
       }

        return Optional.empty();
    }   
}
