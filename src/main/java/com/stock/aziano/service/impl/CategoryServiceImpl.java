package com.stock.aziano.service.impl;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.models.Category;
import com.stock.aziano.repository.CategoryRepository;
import com.stock.aziano.service.CategoryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapToCategoryDto).collect(Collectors.toList());
    }

    private CategoryDto mapToCategoryDto(Category category) {
        return CategoryDto.builder()
                .categoryName(category.getCategoryName())
                .build();
    }
}
