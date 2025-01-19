package com.equinte.gotur.service;

import com.equinte.gotur.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(Customer customer, String message) {
        sendNotification(customer.getId(), message);
    }

    public void sendNotification(Long id, String message) {
        log.info("Sending notifcation to customerId: {}, message:{}", id, message);
    }
}
