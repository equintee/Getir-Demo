package com.equinte.gotur.dao.order.response;

import lombok.Data;

import java.util.List;

@Data
public class GetOrdersResponse {
    private List<OrderDTO> data;
}
