package com.stock.aziano.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name="facility_id", nullable = false)
    private Facility facility;

    @Column(name = "posting_date", nullable = false)
    private LocalDate postingDate;

    @Column(length = 1000)
    private String description;
}