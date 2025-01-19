package com.equinte.gotur.repository;

import com.equinte.gotur.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
