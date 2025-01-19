package com.equinte.gotur.dao.customer.response;

import lombok.Data;

import java.util.List;

@Data
public class GetAllCustomersResponse {
    private List<GetCustomerResponse> data;
}
