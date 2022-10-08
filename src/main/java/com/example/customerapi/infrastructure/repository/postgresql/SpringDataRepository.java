package com.example.customerapi.infrastructure.repository.postgresql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRepository extends CrudRepository<CustomerEntity, Integer>{
    
}
