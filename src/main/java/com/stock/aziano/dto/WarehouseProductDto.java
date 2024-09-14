package com.stock.aziano.dto;

import java.math.BigInteger;

public class WarehouseProductDto {
    private Long facilityProductId;
    private WarehouseDto facility;
    private ProductDto product;
    private BigInteger quantity;
    private BigInteger totalCost;
}