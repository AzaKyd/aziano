package com.stock.aziano.dto;


import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private String productCode;
    private String barcode;
    private String name;
    private Long categoryId;
    private Double costPrice;
    private Double sellingPrice;

}
