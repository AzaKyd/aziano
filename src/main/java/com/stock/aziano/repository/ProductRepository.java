package com.stock.aziano.repository;

import com.stock.aziano.models.FacilityProduct;
import com.stock.aziano.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    Product findByBarcode(String barcode);

    Product findByProductCode(String productCode);
}
