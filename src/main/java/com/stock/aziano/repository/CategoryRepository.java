package com.stock.aziano.repository;

import com.stock.aziano.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
