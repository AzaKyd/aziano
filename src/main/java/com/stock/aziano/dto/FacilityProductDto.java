package com.stock.aziano.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class FacilityProductDto {
    private Long facilityProductId;
    private FacilityDto facility;
    private ProductDto product;
    private BigInteger quantity;
    private BigInteger totalCost;
}