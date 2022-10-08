package com.example.customerapi.application.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerapi.application.request.SaveCustomerRequest;
import com.example.customerapi.application.response.SaveCustomerResponse;
import com.example.customerapi.domain.Customer;
import com.example.customerapi.domain.service.CustomerService;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    SaveCustomerResponse createOrder(@RequestBody final SaveCustomerRequest saveCustomerRequest) {
        Customer saved = this.customerService.save(saveCustomerRequest.getCustomer());
        return new SaveCustomerResponse(saved);
    }
    
}
