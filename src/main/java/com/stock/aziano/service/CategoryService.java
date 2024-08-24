package com.stock.aziano.service;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.models.Category;

import java.util.List;

public interface CategoryService {
    public List<CategoryDto> getCategories();
}
