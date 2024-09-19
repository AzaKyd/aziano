package com.stock.aziano.mappers;

import com.stock.aziano.dto.PostingProductDto;
import com.stock.aziano.models.PostingProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostingProductMapper {
    PostingProductDto toDto(PostingProduct postingProduct);

    PostingProduct toEntity(PostingProductDto postingProductDto);
}