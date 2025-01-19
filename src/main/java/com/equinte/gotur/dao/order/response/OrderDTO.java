package com.equinte.gotur.dao.order.response;

import com.equinte.gotur.entity.Customer;
import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Customer customer;
    private Float itemPrice;
    private Float discountRate;
    private Float amountPaid;
}
