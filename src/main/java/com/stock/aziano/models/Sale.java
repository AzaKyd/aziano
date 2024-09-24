package com.stock.aziano.models;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @NotNull
    @Column(name = "selling_price", nullable = false)
    private BigDecimal sellingPrice;

    @NotNull
    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice;

    @NotNull
    @Column(name = "cash", nullable = false)
    private boolean cash;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "total")
    private BigDecimal total;

    @PrePersist
    protected void onCreate() {
        if (saleDate == null) {
            // Устанавливаем текущее время с учетом тайм-зоны UTC+6 (Бишкек)
            saleDate = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
        }
    }
}