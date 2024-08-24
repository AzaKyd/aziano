package com.stock.aziano.repository.impl;

import com.stock.aziano.models.Category;
import com.stock.aziano.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Optional<Category> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<Category>();
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void delete(int Long) {

    }
}
