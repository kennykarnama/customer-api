package com.example.customerapi.domain;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    private final Integer id;

    @NotEmpty(message = "customer name cannot be empty")
    @Size(min = 1, max = 100, message = "customer name must be in length >= 1 and <= 100")
    private String name;

    @NotEmpty(message = "customer address cannot be empty")
    @Size(min = 1, max = 100, message = "customer address must be in length >= 1 and <= 100")
    private String address;
    
    @JsonCreator
    public Customer(@JsonProperty("id") final int id, @JsonProperty("name") String name, @JsonProperty("address") String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer c = (Customer) o;
        return Objects.equals(id, c.id) && Objects.equals(name, c.name) && Objects.equals(address, c.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
