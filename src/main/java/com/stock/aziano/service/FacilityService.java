package com.stock.aziano.service;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.dto.WarehouseDto;
import com.stock.aziano.models.Warehouse;

import java.util.List;

public interface FacilityService {
    public List<WarehouseDto> getWarehouses();

    public void addWarehouse(WarehouseDto category);

    public WarehouseDto getWarehouseById(Long id);

    public void updateWarehouse(WarehouseDto categoryDto);

    public void removeWarehouse(Long id);
}
