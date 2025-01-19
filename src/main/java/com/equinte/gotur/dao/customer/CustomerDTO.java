package com.equinte.gotur.dao.customer;

import com.equinte.gotur.entity.CustomerTier;
import com.equinte.gotur.entity.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerDTO {
    public LocalDateTime createTime;
    public List<Order> orders;
    private Long id;
    private String username;
    private CustomerTier tier;
}
