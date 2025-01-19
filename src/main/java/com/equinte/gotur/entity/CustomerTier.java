package com.equinte.gotur.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customer_tiers")
public class CustomerTier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Min(0)
    @Max(100)
    @NotNull
    private Float discountRate;

    @Min(0)
    @NotNull
    private Integer minimumOrderCount;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}
