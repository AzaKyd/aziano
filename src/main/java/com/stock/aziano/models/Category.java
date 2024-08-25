package com.stock.aziano.models;

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
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NonNull
    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> product;

    // Getters and Setters
}