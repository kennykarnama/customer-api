package com.example.customerapi.application.request;

import lombok.Data;

@Data
public class PagingRequest {
    private int page = 1;
    
    private int pageSize = 10;
    
    private String sortDirection;
    
    private String sortBy;

    public PagingRequest() {}
}
