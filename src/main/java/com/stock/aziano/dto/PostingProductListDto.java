package com.stock.aziano.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostingProductListDto {
    private List<PostingProductDto> items;
}