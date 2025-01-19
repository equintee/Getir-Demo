package com.equinte.gotur.dao.order.response;

import lombok.Data;

@Data
public class CreateOrderResponse {
    private Long id;
    private Long customerId;
    private String customerTier;
    private Float itemPrice;
    private Float discountRate;
}
