package com.stock.aziano.service;

import com.stock.aziano.dto.FacilityDto;

import java.util.List;

public interface FacilityService {
    public List<FacilityDto> getFacilities();

    public void addFacility(FacilityDto category);

    public FacilityDto getFacilityById(Long id);

    public void updateFacility(FacilityDto categoryDto);

    public void removeFacility(Long id);
}
