package com.stock.aziano.repository;

import com.stock.aziano.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> hasProductName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Product> hasBarcode(String barcode) {
        return (root, query, criteriaBuilder) -> {
            if (barcode == null || barcode.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(
                    root.get("barcode"), "%" + barcode + "%"
            );
        };
    }

    public static Specification<Product> hasProductCode(String productCode) {
        return (root, query, criteriaBuilder) -> {
            if (productCode == null || productCode.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(
                    root.get("productCode"), "%" + productCode + "%"
            );
        };
    }

    // Sorting logic for Product entity
    public static Specification<Product> withSorting(Sort sort) {
        return (root, query, criteriaBuilder) -> {
            if (sort != null && sort.isSorted()) {
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
