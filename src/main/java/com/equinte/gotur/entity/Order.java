package com.equinte.gotur.entity;

import com.equinte.gotur.entity.listeners.OrderListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
@EntityListeners(OrderListener.class)
public class Order {
    @CreationTimestamp
    private LocalDateTime createTime;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //Should be UUID
    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @NotNull
    private Float itemPrice; //Should be list, however I won't implement anything about Item so it's w/e.
    @Min(0)
    @Max(100)
    @NotNull
    private Float discountRate;
    @Min(0)
    @NotNull
    private Float amountPaid;
}
