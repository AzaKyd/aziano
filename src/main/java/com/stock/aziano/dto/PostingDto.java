package com.stock.aziano.dto;

import com.stock.aziano.models.Facility;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostingDto {
    private Long id;
    private Facility facility;
    private Integer quantity;
    private BigDecimal totalSum;
    private LocalDate entryDate;
    private String description;
}
