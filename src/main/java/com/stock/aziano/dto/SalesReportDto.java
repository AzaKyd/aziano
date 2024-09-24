package com.stock.aziano.dto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesReportDto {
    private LocalDate saleDate;
    private BigDecimal totalCostPrice;
    private BigDecimal totalSellingPrice;
    private BigDecimal netProfit;
}
