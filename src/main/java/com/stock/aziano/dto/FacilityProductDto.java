package com.stock.aziano.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class FacilityProductDto {
    private Long id;
    private FacilityDto facility;
    private ProductDto product;
    private BigInteger quantity;
    private BigDecimal cost;
    private BigDecimal totalCost;
}