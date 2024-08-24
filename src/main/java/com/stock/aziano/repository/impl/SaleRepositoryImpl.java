package com.stock.aziano.repository.impl;

import com.stock.aziano.models.Sale;
import com.stock.aziano.repository.SaleRepository;

import java.util.List;
import java.util.Optional;

public class SaleRepositoryImpl implements SaleRepository {
    @Override
    public Optional<Sale> findByProduct(long id) {
        return Optional.empty();
    }

    @Override
    public List<Sale> findByWarehouse(String warehouseName) {
        return List.of();
    }

    @Override
    public List<Sale> findByDate(String date) {
        return List.of();
    }
}
