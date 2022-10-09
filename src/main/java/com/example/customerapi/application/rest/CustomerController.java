package com.example.customerapi.application.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerapi.application.request.CustomerCriteria;
import com.example.customerapi.application.request.PagingRequest;
import com.example.customerapi.application.request.PatchCustomerRequest;
import com.example.customerapi.application.request.SaveCustomerRequest;
import com.example.customerapi.application.response.FindCustomerByIdResponse;
import com.example.customerapi.application.response.ListCustomerResponse;
import com.example.customerapi.application.response.PatchCustomerResponse;
import com.example.customerapi.application.response.SaveCustomerResponse;
import com.example.customerapi.domain.Customer;
import com.example.customerapi.domain.CustomerSearchCriteria;
import com.example.customerapi.domain.PaginatedCustomer;
import com.example.customerapi.domain.Paging;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ListCustomerResponse listCustomers(@Valid CustomerCriteria criteria, PagingRequest pagingRequest) {
        
        // TODO: set separate function to parse
        CustomerSearchCriteria searchCriteria = new CustomerSearchCriteria();
        searchCriteria.setName(criteria.getName());
        searchCriteria.setAddress(criteria.getAddress());

        Paging paging = new Paging();
        paging.setPage(pagingRequest.getPage());
        paging.setPageSize(pagingRequest.getPageSize());
        paging.setSortBy(pagingRequest.getSortBy());
        paging.setSortDirection(pagingRequest.getSortDirection());

        PaginatedCustomer paginated = this.customerService.findAllByCriteria(searchCriteria, paging);
        return new ListCustomerResponse(paginated.getCustomers(), paginated.getNumberOfItems(), paginated.getNumberOfPages());
    }
}
