package com.example.customerapi.infrastructure.repository.postgresql;

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
    public Customer save(Customer customer) {
        CustomerEntity data = new CustomerEntity(customer);
        this.repo.save(data);
        return data.toCustomer();
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
