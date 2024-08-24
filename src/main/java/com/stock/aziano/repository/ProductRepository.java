package com.stock.aziano.repository;

import com.stock.aziano.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String id);

    List<Product> findAll();

    Optional<Product> findByName(String productName);

    void save(Product product);

    void deleteById(Long productId);

    void update(Product product);

}
