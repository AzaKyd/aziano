package com.stock.aziano.mappers;


import com.stock.aziano.dto.SaleDto;
import com.stock.aziano.models.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    Sale toEntity(SaleDto saleDto);

    SaleDto toDto(Sale sale);
}
