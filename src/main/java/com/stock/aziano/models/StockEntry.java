package com.stock.aziano.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock_entry")
public class StockEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NonNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NonNull
    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;
}