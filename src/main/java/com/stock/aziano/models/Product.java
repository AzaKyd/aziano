package com.stock.aziano.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stock.aziano.utils.BarcodeGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;


    @Column(name = "barcode", nullable = false, unique = true)
    private String barcode;

    @NonNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @NonNull
    @Column(name = "cost_price", nullable = false)
    private Double costPrice;

    @NonNull
    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "id")
    )
    private List<Sale> sales;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "entry_product",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stock_entry_id", referencedColumnName = "id")
    )
    private List<StockEntry> stockEntries;

    @Column(length = 1000)  // Указываем длину строки для хранения URL
    private String imageUrl; // Поле для URL-адреса изображения

    @Column(length = 1000)  // Указываем максимальную длину описания
    private String description; // Поле для описания продукта

    @PrePersist
    private void generateProductCode() {
        if (this.productCode == null || this.productCode.isEmpty()) {
            this.productCode = String.format("%05d", this.getId());
        }
        if (this.barcode == null || this.barcode.isEmpty()) {
            this.barcode = BarcodeGenerator.generateBarcode();
        }
    }

   // Getters and Setters
}
