package com.equinte.gotur.controller;

import com.equinte.gotur.dao.order.request.CreateOrderRequest;
import com.equinte.gotur.dao.order.response.CreateOrderResponse;
import com.equinte.gotur.dao.order.response.GetOrdersResponse;
import com.equinte.gotur.dao.order.response.GetOrdersResponseElement;
import com.equinte.gotur.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderService service;

    @GetMapping
    public GetOrdersResponse getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetOrdersResponseElement get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateOrderResponse create(@Valid @RequestBody CreateOrderRequest request) {
        return service.create(request);
    }
}
