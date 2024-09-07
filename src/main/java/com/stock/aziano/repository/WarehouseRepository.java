package com.stock.aziano.repository;


import com.stock.aziano.models.Category;
import com.stock.aziano.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}