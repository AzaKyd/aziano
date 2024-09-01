package com.stock.aziano.controller;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String categories(Model model) {
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {

        return "/forms/category/add-category";
    }
}
