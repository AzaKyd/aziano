package com.stock.aziano.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String productCode;
    private String barcode;
    private String name;
    private Long categoryId;
    private Double costPrice;
    private Double sellingPrice;
    private String imageUrl;
    private String description;
}
