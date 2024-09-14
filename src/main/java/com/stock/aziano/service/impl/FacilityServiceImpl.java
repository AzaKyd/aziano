package com.stock.aziano.service.impl;

import com.stock.aziano.dto.FacilityDto;
import com.stock.aziano.exception.ResourceNotFoundException;
import com.stock.aziano.mappers.FacilityMapper;
import com.stock.aziano.models.Facility;
import com.stock.aziano.repository.FacilityRepository;
import com.stock.aziano.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;

    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository, FacilityMapper facilityMapper) {
        this.facilityRepository = facilityRepository;
        this.facilityMapper = facilityMapper;
    }

    @Override
    public List<FacilityDto> getFacilities() {
        return facilityRepository.findAll().stream().map(facilityMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void addFacility(FacilityDto facilityDto) {
        facilityRepository.save(facilityMapper.toEntity(facilityDto));
    }

    @Override
    public FacilityDto getFacilityById(Long id) {
        Optional<Facility> facility = facilityRepository.findById(id);
        // Проверяем, присутствует ли значение
        if (facility.isPresent()) {
            return facilityMapper.toDto(facility.get());
        } else {
            throw new ResourceNotFoundException("Склад не найден");
        }
    }

    @Override
    public void updateFacility(FacilityDto warehouseDto) {
        Facility facility = facilityRepository.findById(warehouseDto.getId())
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));

        facility.setName(warehouseDto.getName());
        facility.setLocation(warehouseDto.getLocation());
        facilityRepository.save(facility);
    }

    @Override
    public void removeFacility(Long id) {
        try {
            if (!facilityRepository.existsById(id)) {
                throw new ResourceNotFoundException("Категория с ID " + id + " не найдена.");
            }
            facilityRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении категории: " + e.getMessage());
        }
    }
}
