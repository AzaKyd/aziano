package com.stock.aziano.service.impl;

import com.stock.aziano.dto.FacilityProductDto;
import com.stock.aziano.models.FacilityProduct;
import com.stock.aziano.repository.FacilityProductRepository;
import com.stock.aziano.repository.FacilityProductSpecification;
import com.stock.aziano.service.FacilityProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FacilityProductServiceImpl implements FacilityProductService {
    @Autowired
    private FacilityProductRepository facilityProductRepository;

    public Page<FacilityProductDto> getFilteredFacilityProducts(String name, String barcode, String productCode, Pageable pageable) {
        Specification<FacilityProduct> spec = Specification
                .where(FacilityProductSpecification.hasProductName(name))
                .and(FacilityProductSpecification.hasBarcode(barcode))
                .and(FacilityProductSpecification.hasProductCode(productCode));// Пример сортировки по имени продукта

        Page<FacilityProduct> facilityProducts = facilityProductRepository.findAll(spec, pageable);

        // Маппим сущности в DTO
        return facilityProducts.map(this::convertToDto);
    }

    // Метод для конвертации сущности в DTO
    private FacilityProductDto convertToDto(FacilityProduct facilityProduct) {
        FacilityProductDto dto = new FacilityProductDto();
        dto.setId(facilityProduct.getId());// Конвертация Product в DTO
        dto.setProductName(facilityProduct.getProduct().getName());
        dto.setProductCode(facilityProduct.getProduct().getProductCode());
        dto.setImageUrl(facilityProduct.getProduct().getImageUrl());
        dto.setBarcode(facilityProduct.getProduct().getBarcode());
        dto.setQuantity(facilityProduct.getQuantity());
        dto.setCost(facilityProduct.getCost());
        dto.setTotalCost(facilityProduct.getTotalCost());
        return dto;
    }
}
