package com.stock.aziano.repository;

import com.stock.aziano.models.Product;
import com.stock.aziano.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findByProductId(long id);

    List<Sale> findAllBySaleDate(LocalDate date);

}
