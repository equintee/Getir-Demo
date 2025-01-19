package com.equinte.gotur.controller;

import com.equinte.gotur.dao.customer.request.CreateCustomerRequest;
import com.equinte.gotur.dao.customer.response.CreateCustomerResponse;
import com.equinte.gotur.dao.customer.response.GetAllCustomersResponse;
import com.equinte.gotur.dao.customer.response.GetCustomerResponse;
import com.equinte.gotur.service.CustomerService;
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
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/{id}")
    public GetCustomerResponse getCustomerById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public GetAllCustomersResponse getAllCustomers() {
        return service.findAll();
    }

    @PostMapping
    public CreateCustomerResponse create(@RequestBody @Valid CreateCustomerRequest request) {
        return service.create(request);
    }
}
