package com.equinte.gotur.service;

import com.equinte.gotur.dao.order.request.CreateOrderRequest;
import com.equinte.gotur.dao.order.response.CreateOrderResponse;
import com.equinte.gotur.dao.order.response.GetOrdersResponse;
import com.equinte.gotur.dao.order.response.GetOrdersResponseElement;
import com.equinte.gotur.entity.Customer;
import com.equinte.gotur.entity.Order;
import com.equinte.gotur.exceptions.GeneralException;
import com.equinte.gotur.mapper.OrderMapper;
import com.equinte.gotur.repository.OrderRepository;
import com.equinte.gotur.util.ItemPriceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final OrderMapper MAPPER = OrderMapper.INSTANCE;
    private final OrderRepository repository;
    private final CustomerService customerService;

    public GetOrdersResponseElement getById(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new GeneralException(String.format("Order not found. id:%d", id), "Order not found.", HttpStatus.NO_CONTENT));
        return MAPPER.responseElement(order);
    }

    public GetOrdersResponse getAll() {
        List<Order> orders = repository.findAll();
        GetOrdersResponse response = new GetOrdersResponse();
        response.setData(orders.stream().map(MAPPER::responseElement).toList());
        return response;
    }

    public CreateOrderResponse create(CreateOrderRequest request) {
        Customer customer = customerService.findEntityById(request.getCustomerId());

        Float itemPrice = ItemPriceUtil.generateItemPrice();
        Float discountRate = customer.getTier().getDiscountRate();
        Float finalAmount = ItemPriceUtil.calculateDiscountedPrice(itemPrice, discountRate);

        Order order = MAPPER.toEntity(customer, itemPrice, discountRate, finalAmount);
        order = repository.save(order);

        return MAPPER.createOrderResponse(order);
    }
}
