package com.stock.aziano.repository;

import com.stock.aziano.models.FacilityProduct;
import org.springframework.data.jpa.domain.Specification;

public class FacilityProductSpecification {

    public static Specification<FacilityProduct> hasProductName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("product").get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<FacilityProduct> hasBarcode(String barcode) {
        return (root, query, criteriaBuilder) ->
                barcode == null ? null : criteriaBuilder.like(root.get("product").get("barcode"), "%" + barcode + "%");
    }

    public static Specification<FacilityProduct> hasProductCode(String productCode) {
        return (root, query, criteriaBuilder) ->
                productCode == null ? null : criteriaBuilder.like(root.get("product").get("productCode"), "%" + productCode + "%");
    }
}