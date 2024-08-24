package com.stock.aziano.repository.impl;

import com.stock.aziano.models.Warehouse;
import com.stock.aziano.repository.WarehouseRepository;

import java.util.List;

public class WarehouseRepositoryImpl implements WarehouseRepository {
    @Override
    public List<Warehouse> findByName(String name) {
        return List.of();
    }
}
