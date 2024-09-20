package com.stock.aziano.dto;

import com.stock.aziano.models.Facility;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PostingDto {
    public Long id;
    public Facility facility;
    public Integer quantity;
    public BigDecimal totalSum;
    public LocalDate postingDate;
    public String description;
}
