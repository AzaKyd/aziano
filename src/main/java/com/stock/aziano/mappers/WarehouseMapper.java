package com.stock.aziano.mappers;

import com.stock.aziano.dto.WarehouseDto;
import com.stock.aziano.models.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    Warehouse toEntity(WarehouseDto warehouse);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    WarehouseDto toDto(Warehouse warehouse);
}
