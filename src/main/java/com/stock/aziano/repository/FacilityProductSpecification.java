package com.stock.aziano.repository;

import com.stock.aziano.models.FacilityProduct;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class FacilityProductSpecification {

    public static Specification<FacilityProduct> hasProductName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("product").get("name")), "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<FacilityProduct> hasBarcode(String barcode) {
        return (root, query, criteriaBuilder) -> {
            if (barcode == null || barcode.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(
                    root.get("product").get("barcode"), "%" + barcode + "%"
            );
        };
    }

    public static Specification<FacilityProduct> hasProductCode(String productCode) {
        return (root, query, criteriaBuilder) -> {
            if (productCode == null || productCode.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(
                    root.get("product").get("productCode"), "%" + productCode + "%"
            );
        };
    }

    // Добавляем метод для сортировки
    public static Specification<FacilityProduct> withSorting(Sort sort) {
        return (root, query, criteriaBuilder) -> {
            if (sort != null && sort.isSorted()) {
                // Получаем из sort все параметры для сортировки и добавляем их к запросу
                sort.stream().forEach(order -> {
                    if (order.isAscending()) {
                        query.orderBy(criteriaBuilder.asc(root.get(order.getProperty())));
                    } else {
                        query.orderBy(criteriaBuilder.desc(root.get(order.getProperty())));
                    }
                });
            }
            return null;
        };
    }
}