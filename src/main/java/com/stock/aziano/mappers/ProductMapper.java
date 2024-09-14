package com.stock.aziano.mappers;

import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductDto productDto);
}
