package com.equinte.gotur.mapper;

import com.equinte.gotur.dao.order.response.CreateOrderResponse;
import com.equinte.gotur.dao.order.response.GetOrdersResponseElement;
import com.equinte.gotur.entity.Customer;
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
    Order toEntity(Customer customer, Float itemPrice, Float discountRate, Float finalAmount);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerTier", source = "customer.tier.name")
    CreateOrderResponse createOrderResponse(Order order);

    @Mapping(target = "customerId", source = "customer.id")
    GetOrdersResponseElement responseElement(Order e);
}
