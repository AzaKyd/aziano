package com.stock.aziano.repository;

import com.stock.aziano.models.FacilityProduct;
import com.stock.aziano.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityProductRepository extends JpaRepository<FacilityProduct, Long> {
    boolean existsFacilityProductByFacilityIdAndProductId(Long id, Long productId);
    FacilityProduct findFacilityProductByFacilityIdAndProductId(Long id, Long productId);
    FacilityProduct findFacilityProductByProductId(Long productId);
    Page<FacilityProduct> findAll(Specification<FacilityProduct> spec, Pageable pageable);
    void deleteFacilityProductById(Long id);
}
