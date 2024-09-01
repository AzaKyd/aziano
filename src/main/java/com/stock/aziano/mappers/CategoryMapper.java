package com.stock.aziano.mappers;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);
}