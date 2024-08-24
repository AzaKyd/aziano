package com.stock.aziano.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleDto {
    private Long productId;
    private Long warehouseId;
    private Integer quantity;
    private LocalDateTime saleDate;
    private Double sellingPrice;
}
