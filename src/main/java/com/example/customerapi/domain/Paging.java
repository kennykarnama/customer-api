package com.example.customerapi.domain;

import java.util.Objects;

import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class Paging {
    
    private int page;
    
    private int pageSize;
    
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    
    private String sortBy;

    public Paging() {}

    public void setSortDirection(String s) {
        if (Objects.isNull(s)) {
            return;
        }
        this.sortDirection = Sort.Direction.fromString(s);
    }

    public int getPage() {
        return this.page - 1;
    }

}
