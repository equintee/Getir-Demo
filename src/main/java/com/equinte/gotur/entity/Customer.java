package com.equinte.gotur.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @CreationTimestamp
    public LocalDateTime createTime;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    public List<Order> orders;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    @ManyToOne
    private CustomerTier tier;
}
