package com.stock.aziano.mappers;

import com.stock.aziano.dto.FacilityDto;
import com.stock.aziano.models.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacilityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    Facility toEntity(FacilityDto facilityDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    FacilityDto toDto(Facility facility);
}
