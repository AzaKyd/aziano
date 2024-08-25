package com.stock.aziano.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "location", nullable = false)
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockEntry> stockEntries;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    private List<Sale> sales;

    // Getters and Setters
}