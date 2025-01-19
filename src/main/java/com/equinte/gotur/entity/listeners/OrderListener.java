package com.equinte.gotur.entity.listeners;

import com.equinte.gotur.dao.customerTier.response.CustomerTierDTO;
import com.equinte.gotur.entity.Order;
import com.equinte.gotur.service.CustomerService;
import com.equinte.gotur.service.CustomerTierService;
import com.equinte.gotur.service.NotificationService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class OrderListener {
    private static final Logger log = LoggerFactory.getLogger(OrderListener.class);
    private final CustomerService customerService;
    private final CustomerTierService customerTierService;
    private final NotificationService notificationService;

    public OrderListener(@Lazy CustomerService customerService, @Lazy CustomerTierService customerTierService, @Lazy NotificationService notificationService) {
        this.customerService = customerService;
        this.customerTierService = customerTierService;
        this.notificationService = notificationService;
    }

    @PostPersist
    @PostUpdate
    public void checkCustomerTier(Order order) {
        List<CustomerTierDTO> tiers = customerTierService.findAllDto();
        int orderCount = customerService.findById(order.getCustomer().getId()).getOrders().size();

        //Terrible implementation, since orders are sorted we can find the tier of customer using faster search algorithms.
        //However, it's a demo :D

        for (CustomerTierDTO tier : tiers) {
            int minimumOrderCount = tier.getMinimumOrderCount();

            if (orderCount == minimumOrderCount) {
                order.getCustomer().setTier(customerTierService.findById(tier.getId()));
                log.info("CustomerID: {} tier updated to {}", order.getCustomer().getId(), tier.getName());
                break;
            }

            if (orderCount == minimumOrderCount - 1) {
                notificationService.sendNotification(order.getCustomer(), String.format("Make one more order to promote %s tier!", tier.getName()));
                break;
            }

            //Since customer tiers sorted by minimumOrderCount, we can reduce amount of loops by checking if orderCount is less then minimumOrderCount
            if (minimumOrderCount > orderCount) {
                break;
            }
        }
    }
}
