package com.equinte.gotur.dao.customer_tier.response;

import lombok.Data;

@Data
public class CustomerTierDTO {
    private Long id;
    private String name;
    private Float discountRate;
    private Integer minimumOrderCount;
}
