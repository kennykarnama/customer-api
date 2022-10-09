package com.example.customerapi.infrastructure.repository.postgresql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRepository extends JpaRepository<CustomerEntity, Integer>, SpringDataCustomRepository {    
    Page<CustomerEntity> findAllByCustNameAndCustAddressContains(String custName, String custAddress, Pageable pageable);
}
