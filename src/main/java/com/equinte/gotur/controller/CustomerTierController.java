package com.equinte.gotur.controller;

import com.equinte.gotur.dao.customerTier.request.UpdateCustomerTierRequest;
import com.equinte.gotur.dao.customerTier.response.CustomerTierDTO;
import com.equinte.gotur.service.CustomerTierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer/tier")
public class CustomerTierController {
    private final CustomerTierService service;

    @GetMapping
    public List<CustomerTierDTO> findAll() {
        return service.findAllDto();
    }

    @PatchMapping
    public CustomerTierDTO update(@Valid @RequestBody UpdateCustomerTierRequest request) {
        return service.update(request);
    }
}
