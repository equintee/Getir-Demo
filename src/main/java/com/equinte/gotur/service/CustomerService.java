package com.equinte.gotur.service;


import com.equinte.gotur.dao.customer.CustomerDTO;
import com.equinte.gotur.dao.customer.request.CreateCustomerRequest;
import com.equinte.gotur.dao.customer.response.CreateCustomerResponse;
import com.equinte.gotur.dao.customer.response.GetAllCustomersResponse;
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
    private final CustomerRepository repository;
    private final CustomerTierService customerTierService;
    private final CustomerMapper mapper = CustomerMapper.INSTANCE;

    public CreateCustomerResponse create(CreateCustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setTier(customerTierService.getProperCustomerTier(0));
        customer = repository.save(customer);

        return mapper.createResponse(customer);
    }

    public CustomerDTO findDtoById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new GeneralException(String.format("Customer not found. id:%d", id), "Customer not found.", HttpStatus.NO_CONTENT));
        return mapper.toDto(customer);
    }

    public Customer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new GeneralException(String.format("Customer not found. id:%d", id), "Customer not found.", HttpStatus.NO_CONTENT));
    }

    public GetAllCustomersResponse findAll() {
        GetAllCustomersResponse response = new GetAllCustomersResponse();
        response.setData(repository.findAll().stream().map(mapper::toResponseElement).toList());
        return response;
    }

    public List<CustomerDTO> findAllDtos() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
