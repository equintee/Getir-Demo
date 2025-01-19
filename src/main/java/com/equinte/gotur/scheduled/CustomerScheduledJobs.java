package com.equinte.gotur.scheduled;

import com.equinte.gotur.dao.customer.CustomerDTO;
import com.equinte.gotur.dao.customer_tier.response.CustomerTierDTO;
import com.equinte.gotur.exceptions.GeneralException;
import com.equinte.gotur.service.CustomerService;
import com.equinte.gotur.service.CustomerTierService;
import com.equinte.gotur.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class CustomerScheduledJobs {
    private static final Logger log = LoggerFactory.getLogger(CustomerScheduledJobs.class);
    private final CustomerService customerService;
    private final CustomerTierService customerTierService;
    private final NotificationService notificationService;

    //This is unnecessary because I am already setting customer tier when they place order.
    //I'm just implementing this because it's in requirements.
    @Scheduled(cron = "1 * * * * *")
    public void updateCustomerTierJob() {
        log.info("Customer tier update job started.");
        List<CustomerDTO> customers = customerService.findAllDtos();
        //This should be cached using redis or w/e. For now, I didn't wanted to execute database query everytime, so I will just pass as argument.
        List<CustomerTierDTO> tiers = customerTierService.findAllDto();
        CountDownLatch countDownLatch = new CountDownLatch(customers.size());
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (CustomerDTO customer : customers) {
            executorService.submit(() -> updateCustomerTier(customer, tiers, countDownLatch));
        }

        try {
            countDownLatch.await();
            log.info("Customer tier update job finished.");
        } catch (InterruptedException e) {
            log.error("Customer tier update job failed.");
            //Should throw special exception. Lazy implementation
            throw new GeneralException("Job Failed", "Job Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            executorService.shutdown();
        }
    }

    public void updateCustomerTier(CustomerDTO customerDTO, List<CustomerTierDTO> tiers, CountDownLatch countDownLatch) {
        //Same implementation on Order listener.
        int orderCount = customerDTO.getOrders().size();

        for (CustomerTierDTO tier : tiers) {
            int minimumOrderCount = tier.getMinimumOrderCount();

            if (orderCount == minimumOrderCount && !Objects.equals(customerDTO.getTier().getId(), tier.getId())) {
                customerService.findEntityById(customerDTO.getId()).setTier(customerTierService.findById(tier.getId()));
                log.info("CustomerID: {} tier updated to {}", customerDTO.getId(), tier.getName());
            }

            if (orderCount == minimumOrderCount - 1) {
                notificationService.sendNotification(customerDTO.getId(), String.format("Make one more order to promote %s tier!", tier.getName()));
            }

            //Since customer tiers sorted by minimumOrderCount, we can reduce amount of loops by checking if orderCount is less then minimumOrderCount
            if (minimumOrderCount > orderCount) {
                break;
            }
        }


        countDownLatch.countDown();
    }
}
