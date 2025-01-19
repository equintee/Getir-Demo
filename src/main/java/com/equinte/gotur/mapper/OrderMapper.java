package com.equinte.gotur.mapper;

import com.equinte.gotur.dao.customer.CustomerDTO;
import com.equinte.gotur.dao.order.response.CreateOrderResponse;
import com.equinte.gotur.dao.order.response.OrderDTO;
import com.equinte.gotur.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "amountPaid", source = "finalAmount")
    Order toEntity(CustomerDTO customer, Float itemPrice, Float discountRate, Float finalAmount);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerTier", source = "customer.tier.name")
    CreateOrderResponse createOrderResponse(Order order);

    OrderDTO responseElement(Order e);
}
