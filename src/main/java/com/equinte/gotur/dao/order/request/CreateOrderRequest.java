package com.equinte.gotur.dao.order.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequest {
    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;
}
