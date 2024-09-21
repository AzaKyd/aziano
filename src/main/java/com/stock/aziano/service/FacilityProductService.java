package com.stock.aziano.service;

import com.stock.aziano.dto.FacilityProductDto;
import com.stock.aziano.models.FacilityProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacilityProductService {

    Page<FacilityProductDto> getFilteredFacilityProducts(String name, String barcode, String productCode, Pageable pageable);

}
