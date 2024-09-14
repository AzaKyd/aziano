package com.stock.aziano.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Posting {
    private Long warehouseId;
    private Integer quantity;
    private LocalDateTime entryDate;

}
