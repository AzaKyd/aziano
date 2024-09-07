package com.stock.aziano.service.impl;

import com.stock.aziano.dto.WarehouseDto;
import com.stock.aziano.exception.ResourceNotFoundException;
import com.stock.aziano.mappers.WarehouseMapper;
import com.stock.aziano.models.Category;
import com.stock.aziano.models.Warehouse;
import com.stock.aziano.repository.WarehouseRepository;
import com.stock.aziano.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Autowired
    public FacilityServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public List<WarehouseDto> getWarehouses() {
        return warehouseRepository.findAll().stream().map(warehouseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void addWarehouse(WarehouseDto warehouseDto) {
        warehouseRepository.save(warehouseMapper.toEntity(warehouseDto));
    }

    @Override
    public WarehouseDto getWarehouseById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        // Проверяем, присутствует ли значение
        if (warehouse.isPresent()) {
            return warehouseMapper.toDto(warehouse.get());
        } else {
            throw new ResourceNotFoundException("Склад не найден");
        }
    }

    @Override
    public void updateWarehouse(WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseRepository.findById(warehouseDto.getId())
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));

        warehouse.setName(warehouseDto.getName());
        warehouse.setLocation(warehouseDto.getLocation());
        warehouseRepository.save(warehouse);
    }

    @Override
    public void removeWarehouse(Long id) {
        try {
            if (!warehouseRepository.existsById(id)) {
                throw new ResourceNotFoundException("Категория с ID " + id + " не найдена.");
            }
            warehouseRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении категории: " + e.getMessage());
        }
    }
}
