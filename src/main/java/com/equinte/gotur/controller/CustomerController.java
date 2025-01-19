package com.equinte.gotur.controller;

import com.equinte.gotur.dao.customer.CustomerDTO;
import com.equinte.gotur.dao.customer.response.GetAllCustomersResponse;
import com.equinte.gotur.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@RequestParam Long id) {
        return service.findDtoById(id);
    }

    @GetMapping
    public GetAllCustomersResponse getAllCustomers() {
        return service.findAll();
    }
}
