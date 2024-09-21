package com.stock.aziano.repository;

import com.stock.aziano.models.FacilityProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityProductRepository extends JpaRepository<FacilityProduct, Long> {
    boolean existsFacilityProductByFacilityIdAndProductId(Long id, Long productId);
    FacilityProduct findFacilityProductByFacilityIdAndProductId(Long id, Long productId);
    Page<FacilityProduct> findAll(Specification<FacilityProduct> spec, Pageable pageable);
}
