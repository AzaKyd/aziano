package com.stock.aziano.mappers;


import com.stock.aziano.dto.FacilityProductDto;
import com.stock.aziano.models.FacilityProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacilityProductMapper {

    FacilityProductDto toDto(FacilityProduct facilityProduct);
    FacilityProduct toEntity(FacilityProductDto postingProductDto);
}
