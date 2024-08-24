package com.stock.aziano.repository;


import com.stock.aziano.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WarehouseRepository {
    // Стандартные CRUD методы предоставляются JpaRepository
    List<Warehouse> findByName(String name);
}