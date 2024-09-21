package com.stock.aziano.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SaleDto {
    private Long productId;
    private Long warehouseId;
    private Integer quantity;
    private LocalDateTime saleDate;
    private BigDecimal sellingPrice;
    private BigDecimal discount;
    private boolean cash;
}
