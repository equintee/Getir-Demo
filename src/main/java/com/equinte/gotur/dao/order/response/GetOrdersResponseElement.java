package com.equinte.gotur.dao.order.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetOrdersResponseElement {
    private LocalDateTime createTime;
    private Long id; //Should be UUID
    private Long customerId;
    private Float itemPrice; //Should be list, however I won't implement anything about Item so it's w/e.
    private Float discountRate;
    private Float amountPaid;
}
