package com.stock.aziano.dto;

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
    private String productName;
    private String productCode;
    private String barcode;
    private String imageUrl;
    private BigInteger quantity;
    private BigDecimal costPrice;
    private BigDecimal cost;
    private BigDecimal totalCost;
}