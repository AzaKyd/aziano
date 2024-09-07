package com.stock.aziano.mappers;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "categoryName", target = "categoryName")
    Category toEntity(CategoryDto categoryDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "categoryName", target = "categoryName")
    CategoryDto toDto(Category category);
}