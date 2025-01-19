package com.equinte.gotur.service;

import com.equinte.gotur.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void sendNotification() {
        assertDoesNotThrow(() -> notificationService.sendNotification(new Customer(), "Hope this works wooo!!"));
    }
}