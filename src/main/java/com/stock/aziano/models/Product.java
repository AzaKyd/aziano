package com.stock.aziano.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", insertable=false, updatable=false)
    private Long productId;

    @Column(unique = true)
    private String productCode;

    @Column(unique = true)
    private String barcode;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false)
    private Double costPrice;

    @Column(nullable = false)
    private Double sellingPrice;

    @ManyToMany(mappedBy = "products")
    private Set<Sale> sales;

    @Column(length = 1000)  // Указываем длину строки для хранения URL
    private String imageUrl; // Поле для URL-адреса изображения

    @Column(length = 1000)  // Указываем максимальную длину описания
    private String description; // Поле для описания продукта

    @PrePersist
    private void generateProductCode() {
        if (this.productCode == null || this.productCode.isEmpty()) {
            this.productCode = String.format("%05d", this.productId);
        }
    }

    // Getters and Setters
}