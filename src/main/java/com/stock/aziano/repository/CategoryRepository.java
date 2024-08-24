package com.stock.aziano.repository;

import com.stock.aziano.models.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository {

    Optional<Category> findById(String id);

    Optional<Category> findByName(String name);

    List<Category> findAll();

    void update(Category category);

    void save(Category category);

    void delete(int Long);
}
