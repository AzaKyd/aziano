package com.stock.aziano.repository;

import com.stock.aziano.models.Product;
import com.stock.aziano.models.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Optional<Sale> findByProduct(long id);

    List<Sale> findByWarehouse(String warehouseName);

    List<Sale> findByDate(String date);
}
