package com.equinte.gotur.dao.customer_tier.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCustomerTierRequest {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @Min(0)
    @Max(100)
    @NotNull
    private Float discountRate;

    @NotNull
    @Min(0)
    private Integer minimumOrderCount;
}
