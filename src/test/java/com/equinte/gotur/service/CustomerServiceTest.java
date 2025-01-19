package com.equinte.gotur.service;

import com.equinte.gotur.dao.customer.request.CreateCustomerRequest;
import com.equinte.gotur.dao.customer.response.CreateCustomerResponse;
import com.equinte.gotur.entity.Customer;
import com.equinte.gotur.exceptions.GeneralException;
import com.equinte.gotur.mapper.CustomerMapper;
import com.equinte.gotur.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private final CustomerMapper mapper = CustomerMapper.INSTANCE;
    @Mock
    private CustomerRepository repository;
    @Mock
    private CustomerTierService tierService;
    @InjectMocks
    private CustomerService service;

    @Test
    void create() {
        Customer customer = new Customer();
        String customerName = "New customer";
        customer.setUsername(customerName);

        when(repository.save(customer)).thenReturn(customer);
        when(tierService.getProperCustomerTier(any())).thenReturn(null);

        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setUsername(customerName);

        CreateCustomerResponse response = service.create(request);

        assertEquals(customerName, response.getUsername());
    }

    @Test
    void findById() {
        Customer customer = new Customer();
        long customerId = 1L;
        customer.setId(customerId);

        when(repository.findById(customerId)).thenReturn(Optional.of(customer));
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertEquals(customerId, service.findById(customerId).getId());
        assertThrows(GeneralException.class, () -> service.findById(customerId + 1));
    }

    @Test
    void findEntityById() {
        Customer customer = new Customer();
        long customerId = 1L;
        customer.setId(customerId);

        when(repository.findById(customerId)).thenReturn(Optional.of(customer));
        when(repository.findById(2L)).thenReturn(Optional.empty());

        assertEquals(customer, service.findEntityById(customerId));
        assertThrows(GeneralException.class, () -> service.findEntityById(customerId + 1));
    }

    @Test
    void findAll() {
        long listSize = 5;
        List<Customer> customers = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customers.add(customer);
        }

        when(repository.findAll()).thenReturn(customers);

        assertEquals(listSize, service.findAll().getData().size());
    }

    @Test
    void findAllDtos() {
        long listSize = 5;
        List<Customer> customers = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customers.add(customer);
        }

        when(repository.findAll()).thenReturn(customers);

        assertEquals(listSize, service.findAllDtos().size());
    }

}