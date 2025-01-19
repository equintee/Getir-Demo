package com.equinte.gotur.dao.customer.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCustomerRequest {
    @NotNull
    private String username;
}
