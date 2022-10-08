package com.example.customerapi.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    private final int id;
    private String name;
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
