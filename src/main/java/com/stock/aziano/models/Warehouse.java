package com.stock.aziano.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

}