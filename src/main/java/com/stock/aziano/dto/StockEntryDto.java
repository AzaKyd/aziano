package com.stock.aziano.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockEntryDto {
    private Long warehouseId;
    private Integer quantity;
    private LocalDateTime entryDate;

}
