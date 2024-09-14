package com.stock.aziano.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posting")
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_sum")
    private BigInteger totalSum;

    @Column(name = "posting_date", nullable = false)
    private LocalDateTime postingDate;
}