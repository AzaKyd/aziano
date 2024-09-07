package com.stock.aziano.mappers;

import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
