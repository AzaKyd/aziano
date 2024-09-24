package com.stock.aziano.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SaleDto {
    private Long productId;
    private ProductDto product;
    private Integer quantity;
    private LocalDateTime saleDate;
    private BigDecimal sellingPrice;
    private BigDecimal discount;
    private BigDecimal total;
    private boolean cash;
}
