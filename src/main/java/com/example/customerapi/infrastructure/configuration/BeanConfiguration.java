package com.example.customerapi.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.customerapi.Application;
import com.example.customerapi.domain.repository.CustomerRepository;
import com.example.customerapi.domain.service.CustomerDomainService;
import com.example.customerapi.domain.service.CustomerService;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class BeanConfiguration {

    @Bean
    CustomerService customerService(final CustomerRepository customerRepository) {
        return new CustomerDomainService(customerRepository);
    }
}
