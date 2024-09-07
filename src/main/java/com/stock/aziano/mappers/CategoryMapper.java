package com.stock.aziano.mappers;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);
}