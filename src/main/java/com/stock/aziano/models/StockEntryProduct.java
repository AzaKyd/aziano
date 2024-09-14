package com.stock.aziano.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock_entry_prooduct")
public class StockEntryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entry_id", nullable = false)
    private StockEntry stockEntry;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private BigInteger quantity;

    @Column(name = "cost", nullable = false)
    private BigInteger cost;

    @Column(name = "total_cost", nullable = false)
    private BigInteger totalCost;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;
}
