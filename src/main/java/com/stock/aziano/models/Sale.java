package com.stock.aziano.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> products;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

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