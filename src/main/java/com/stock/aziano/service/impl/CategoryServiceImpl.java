package com.stock.aziano.service.impl;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.exception.DataIsUniqueException;
import com.stock.aziano.exception.ResourceNotFoundException;
import com.stock.aziano.mappers.CategoryMapper;
import com.stock.aziano.models.Category;
import com.stock.aziano.repository.CategoryRepository;
import com.stock.aziano.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> test = categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        return test;
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        // Проверка на уникальность
        if (categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
            throw new DataIsUniqueException("Категория с таким именем уже существует");
        }
        Category category = categoryMapper.toEntity(categoryDto);
        categoryRepository.save(category);
    }

    @Override
    public CategoryDto getCategoryCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        // Проверяем, присутствует ли значение
        if (category.isPresent()) {
            return categoryMapper.toDto(category.get());
        } else {
            throw new ResourceNotFoundException("Категория не найдена");
        }
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));

        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(category);
    }

    public void removeCategory(Long id) {
        try {
            if (!categoryRepository.existsById(id)) {
                throw new ResourceNotFoundException("Категория с ID " + id + " не найдена.");
            }
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении категории: " + e.getMessage());
        }
    }
}
