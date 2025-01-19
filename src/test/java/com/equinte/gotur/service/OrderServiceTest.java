package com.equinte.gotur.service;

import com.equinte.gotur.dao.order.request.CreateOrderRequest;
import com.equinte.gotur.dao.order.response.CreateOrderResponse;
import com.equinte.gotur.entity.Customer;
import com.equinte.gotur.entity.CustomerTier;
import com.equinte.gotur.entity.Order;
import com.equinte.gotur.exceptions.GeneralException;
import com.equinte.gotur.mapper.OrderMapper;
import com.equinte.gotur.repository.OrderRepository;
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
class OrderServiceTest {

    private final OrderMapper mapper = OrderMapper.INSTANCE;
    @Mock
    private OrderRepository repository;
    @Mock
    private CustomerService customerService;
    @InjectMocks
    private OrderService service;

    @Test
    void getById() {
        long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        when(repository.findById(orderId)).thenReturn(Optional.of(order));
        when(repository.findById(orderId + 1)).thenReturn(Optional.empty());

        assertEquals(orderId, service.getById(orderId).getId());
        assertThrows(GeneralException.class, () -> service.getById(orderId + 1));
    }

    @Test
    void getAll() {
        int listSize = 5;

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            Order order = new Order();
            order.setId((long) i);
            orders.add(order);
        }

        when(repository.findAll()).thenReturn(orders);

        assertEquals(listSize, service.getAll().getData().size());
    }

    @Test
    void create() {
        long customerId = 2L;
        Customer customer = new Customer();
        customer.setId(customerId);
        CustomerTier customerTier = new CustomerTier();
        customerTier.setDiscountRate(0.1f);
        customer.setTier(customerTier);

        long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setCustomer(customer);


        when(customerService.findEntityById(customerId)).thenReturn(customer);
        when(repository.save(any(Order.class))).thenReturn(order);

        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerId(customerId);

        CreateOrderResponse actual = service.create(request);
        assertEquals(orderId, actual.getId());
        assertEquals(customerId, actual.getCustomerId());

    }
}