package com.equinte.gotur.dao.customer.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetCustomerResponse {
    private LocalDateTime createTime;
    private Integer orderCount;
    private Long id;
    private String username;
    private String customerTierName;
}
