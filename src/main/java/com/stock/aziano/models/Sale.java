package com.stock.aziano.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @NonNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NonNull
    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @NonNull
    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;

    @Column(name = "discount")
    private Double discount;
}