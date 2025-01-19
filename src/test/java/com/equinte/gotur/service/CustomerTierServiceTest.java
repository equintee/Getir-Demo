package com.equinte.gotur.service;

import com.equinte.gotur.dao.customerTier.request.UpdateCustomerTierRequest;
import com.equinte.gotur.dao.customerTier.response.CustomerTierDTO;
import com.equinte.gotur.entity.CustomerTier;
import com.equinte.gotur.exceptions.GeneralException;
import com.equinte.gotur.mapper.CustomerTierMapper;
import com.equinte.gotur.repository.CustomerTierRepository;
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
class CustomerTierServiceTest {
    private final CustomerTierMapper mapper = CustomerTierMapper.INSTANCE;
    @Mock
    private CustomerTierRepository repository;
    @InjectMocks
    private CustomerTierService service;

    @Test
    void findAllDto() {
        long listSize = 5;
        List<CustomerTier> tiers = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            CustomerTier tier = new CustomerTier();
            tier.setId(i);
            tier.setMinimumOrderCount((int) i);
            tiers.add(tier);
        }

        when(repository.findAll()).thenReturn(tiers);

        assertEquals(listSize, service.findAllDto().size());
    }

    @Test
    void findById() {
        long tierId = 1L;
        CustomerTier tier = new CustomerTier();
        tier.setId(tierId);
        tier.setMinimumOrderCount(5);

        when(repository.findById(tierId)).thenReturn(Optional.of(tier));
        when(repository.findById(tierId + 1)).thenReturn(Optional.empty());

        assertEquals(tierId, service.findById(tierId).getId());
        assertThrows(GeneralException.class, () -> service.findById(tierId + 1));
    }

    @Test
    void update() {
        long tierId = 1L;
        String oldTierName = "Diamond";
        int oldOrderCount = 5;
        int newOrderCount = 10;

        CustomerTier oldTier = new CustomerTier();
        oldTier.setId(tierId);
        oldTier.setName(oldTierName);
        oldTier.setMinimumOrderCount(5);

        String newTierName = "Diamond Plus";
        CustomerTier newTier = new CustomerTier();
        newTier.setId(tierId);
        newTier.setName(newTierName);
        newTier.setMinimumOrderCount(newOrderCount);

        when(repository.findById(tierId)).thenReturn(Optional.of(oldTier));
        when(repository.findById(tierId + 1)).thenThrow(GeneralException.class);
        when(repository.save(any(CustomerTier.class))).thenReturn(newTier);

        UpdateCustomerTierRequest successfulRequest = new UpdateCustomerTierRequest();
        successfulRequest.setId(tierId);
        successfulRequest.setName(newTierName);
        successfulRequest.setMinimumOrderCount(10);

        CustomerTierDTO response = service.update(successfulRequest);
        assertEquals(newTierName, response.getName());
        assertEquals(newOrderCount, response.getMinimumOrderCount());

        UpdateCustomerTierRequest failRequest = new UpdateCustomerTierRequest();
        failRequest.setId(tierId + 1);
        failRequest.setName(newTierName);
        assertThrows(GeneralException.class, () -> service.update(failRequest));
    }

    @Test
    void getProperCustomerTierTest() {
        long listSize = 5;
        List<CustomerTier> tiers = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            CustomerTier tier = new CustomerTier();
            tier.setId(i);
            tier.setMinimumOrderCount((int) i * 5);
            tiers.add(tier);
        }

        when(repository.findAll()).thenReturn(tiers);

        assertEquals(tiers.get(0), service.getProperCustomerTier(4));
        assertEquals(tiers.get(1), service.getProperCustomerTier(5));
        assertEquals(tiers.get(1), service.getProperCustomerTier(7));
    }
}