package com.example.customerapi.infrastructure.repository.postgresql;

import org.springframework.data.domain.Page;

import com.example.customerapi.domain.CustomerSearchCriteria;
import com.example.customerapi.domain.Paging;

public interface SpringDataCustomRepository {
    Page<CustomerEntity> findAllWithFilters(CustomerSearchCriteria c, Paging paging);
}
