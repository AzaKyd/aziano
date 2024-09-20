package com.stock.aziano.repository;

import com.stock.aziano.models.FacilityProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityProductRepository extends JpaRepository<FacilityProduct, Long> {
    boolean existsFacilityProductByFacilityIdAndProductId(Long id, Long productId);
    FacilityProduct findFacilityProductByFacilityIdAndProductId(Long id, Long productId);
}
