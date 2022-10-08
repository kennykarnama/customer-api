package com.example.customerapi.application.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerapi.application.request.PatchCustomerRequest;
import com.example.customerapi.application.request.SaveCustomerRequest;
import com.example.customerapi.application.response.FindCustomerByIdResponse;
import com.example.customerapi.application.response.PatchCustomerResponse;
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
    SaveCustomerResponse saveCustomers(@RequestBody final SaveCustomerRequest saveCustomerRequest) {
        List<Customer> savedCustomers = this.customerService.saveAll(saveCustomerRequest.getCustomers());
        return new SaveCustomerResponse(savedCustomers);
    }
    
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    FindCustomerByIdResponse findCustomerById(@PathVariable Integer id) {
        Optional<Customer> customer = this.customerService.findById(id);
        if (!customer.isPresent()) {
           return null;
        }
        return new FindCustomerByIdResponse(customer.get());
    }

    @DeleteMapping(path="/{id}")
    void deleteCustomerById(@PathVariable Integer id) {
        this.customerService.deleteById(id);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    PatchCustomerResponse patchCustomers(@RequestBody final PatchCustomerRequest updateCustomerRequest) {
        List<Customer> updated = this.customerService.updatePartials(updateCustomerRequest.getCustomers());
        return new PatchCustomerResponse(updated);
    }
}
