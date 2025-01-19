package com.equinte.gotur.service;


import com.equinte.gotur.dao.customer.CustomerDTO;
import com.equinte.gotur.dao.customer.request.CreateCustomerRequest;
import com.equinte.gotur.dao.customer.response.CreateCustomerResponse;
import com.equinte.gotur.dao.customer.response.GetAllCustomersResponse;
import com.equinte.gotur.dao.customer.response.GetCustomerResponse;
import com.equinte.gotur.entity.Customer;
import com.equinte.gotur.exceptions.GeneralException;
import com.equinte.gotur.mapper.CustomerMapper;
import com.equinte.gotur.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private static final CustomerMapper MAPPER = CustomerMapper.INSTANCE;
    private final CustomerRepository repository;
    private final CustomerTierService customerTierService;

    public CreateCustomerResponse create(CreateCustomerRequest request) {
        Customer customer = MAPPER.toEntity(request);
        customer.setTier(customerTierService.getProperCustomerTier(0));
        customer = repository.save(customer);

        return MAPPER.createResponse(customer);
    }

    public GetCustomerResponse findById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new GeneralException(String.format("Customer not found. id:%d", id), "Customer not found.", HttpStatus.NO_CONTENT));
        return MAPPER.toResponseElement(customer);
    }

    public Customer findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new GeneralException(String.format("Customer not found. id:%d", id), "Customer not found.", HttpStatus.NO_CONTENT));
    }

    public GetAllCustomersResponse findAll() {
        GetAllCustomersResponse response = new GetAllCustomersResponse();
        response.setData(repository.findAll().stream().map(MAPPER::toResponseElement).toList());
        return response;
    }

    public List<CustomerDTO> findAllDtos() {
        return repository.findAll().stream().map(MAPPER::toDto).toList();
    }
}
