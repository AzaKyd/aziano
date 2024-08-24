package com.stock.aziano.repository.impl;

import com.stock.aziano.models.Product;
import com.stock.aziano.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Optional<Product> findByName(String productName) {
        return Optional.empty();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(Long productId) {

    }

    @Override
    public void update(Product product) {

    }
}
