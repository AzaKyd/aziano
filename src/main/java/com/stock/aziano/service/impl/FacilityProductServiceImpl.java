package com.stock.aziano.service.impl;

import com.stock.aziano.models.FacilityProduct;
import com.stock.aziano.repository.FacilityProductRepository;
import com.stock.aziano.repository.FacilityProductSpecification;
import com.stock.aziano.service.FacilityProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FacilityProductServiceImpl implements FacilityProductService {
    @Autowired
    private FacilityProductRepository facilityProductRepository;

    public Page<FacilityProduct> getFilteredFacilityProducts(String name, String barcode, String productCode, Pageable pageable) {
        Specification<FacilityProduct> spec = Specification
                .where(FacilityProductSpecification.hasProductName(name))
                .and(FacilityProductSpecification.hasBarcode(barcode))
                .and(FacilityProductSpecification.hasProductCode(productCode));

        return facilityProductRepository.findAll(spec, pageable);
    }
}
