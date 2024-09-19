package com.stock.aziano.dto;

import com.stock.aziano.models.Facility;
import com.stock.aziano.models.Posting;
import com.stock.aziano.models.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostingProductDto {

    private Long id;

    private Posting posting;

    private Facility facility;

    private Product product;

    private BigInteger quantity;

    private BigDecimal  cost;

    private BigDecimal totalCost;

}
