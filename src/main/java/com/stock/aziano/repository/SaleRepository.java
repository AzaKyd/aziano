package com.stock.aziano.repository;

import com.stock.aziano.models.Product;
import com.stock.aziano.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Optional<Sale> findByProductId(long id);

    @Query("SELECT s FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    List<Sale> findSalesForDay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
