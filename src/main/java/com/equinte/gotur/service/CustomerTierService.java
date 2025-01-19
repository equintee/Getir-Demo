package com.equinte.gotur.service;

import com.equinte.gotur.dao.customer_tier.request.UpdateCustomerTierRequest;
import com.equinte.gotur.dao.customer_tier.response.CustomerTierDTO;
import com.equinte.gotur.entity.CustomerTier;
import com.equinte.gotur.exceptions.GeneralException;
import com.equinte.gotur.mapper.CustomerTierMapper;
import com.equinte.gotur.repository.CustomerTierRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerTierService {
    private static final Logger log = LoggerFactory.getLogger(CustomerTierService.class);
    private static final CustomerTierMapper MAPPER = CustomerTierMapper.INSTANCE;
    private final CustomerTierRepository repository;

    public List<CustomerTierDTO> findAllDto() {
        return repository.findAll().stream().map(MAPPER::toDto).sorted(Comparator.comparing(CustomerTierDTO::getMinimumOrderCount)).toList();
    }

    public CustomerTier findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new GeneralException(String.format("Customer Tier not found. id:%d", id), "Customer Tier not found.", HttpStatus.UNPROCESSABLE_ENTITY));
    }
    /*
    create logic could be implemented
     */

    public CustomerTierDTO update(UpdateCustomerTierRequest request) {
        CustomerTier customerTier = repository.findById(request.getId()).orElseThrow(() -> new GeneralException(String.format("Customer Tier not found. id:%d", request.getId()), "Customer Tier not found.", HttpStatus.UNPROCESSABLE_ENTITY));

        customerTier = MAPPER.updateEntity(customerTier, request);
        customerTier = repository.save(customerTier);

        log.info("Customer Tier updated. id:{}", customerTier.getId());
        return MAPPER.toDto(customerTier);
    }

    public CustomerTier getProperCustomerTier(Integer orderCount) {
        List<CustomerTier> tiers = repository.findAll().stream().sorted(Comparator.comparing(CustomerTier::getMinimumOrderCount)).toList();

        CustomerTier properTier = null;
        for (CustomerTier tier : tiers) {
            int minimumOrderCount = tier.getMinimumOrderCount();

            if (orderCount >= minimumOrderCount) {
                properTier = tier;
            }

            //Since customer tiers sorted by minimumOrderCount, we can reduce amount of loops by checking if orderCount is less then minimumOrderCount
            if (minimumOrderCount > orderCount) {
                break;
            }
        }

        return properTier;
    }
}
