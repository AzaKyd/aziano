package com.stock.aziano.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostingDto {
    private Long id;
    private Integer quantity;
    private BigInteger totalSum;
    private LocalDateTime entryDate;
    private String description;
}
