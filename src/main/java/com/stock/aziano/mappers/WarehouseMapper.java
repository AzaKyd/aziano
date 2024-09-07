package com.stock.aziano.mappers;

import com.stock.aziano.dto.WarehouseDto;
import com.stock.aziano.models.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    Warehouse toEntity(WarehouseDto warehouse);
    WarehouseDto toDto(Warehouse warehouse);
}
