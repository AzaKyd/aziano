package com.stock.aziano.controller;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.exception.DataIsUniqueException;
import com.stock.aziano.models.Category;
import com.stock.aziano.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "category";
    }

    @GetMapping("/notification")
    public String notification(Model model) {
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "notifications";
    }

    @GetMapping("/add")
    public String addCategory() {
        return "/forms/category/add-category";
    }

    @PostMapping("/add")
    public String saveCategory(@ModelAttribute("category") CategoryDto category, RedirectAttributes redirectAttributes) {
        try {
            categoryService.addCategory(category);
            redirectAttributes.addFlashAttribute("message", "Категория успешно сохранена!");
            return "redirect:/category";
        } catch (DataIsUniqueException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/category/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        CategoryDto categoryDto = categoryService.getCategoryCategoryById(id);
        model.addAttribute("category", categoryDto);
        return "/forms/category/edit-category"; // Возвращает имя шаблона HTML для редактирования
    }

    @PostMapping("/update")
    public String updateCategory( @ModelAttribute("categoryDto") CategoryDto categoryDto,
                                 RedirectAttributes redirectAttributes) {
        try {
            categoryService.updateCategory(categoryDto);
            redirectAttributes.addFlashAttribute("message", "Категория успешно обновлена!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при обновлении категории.".concat(e.getMessage()));
        }
        return "redirect:/category";
    }

    @GetMapping("/remove/{id}")
    public String removeCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.removeCategory(id);
            redirectAttributes.addFlashAttribute("message", "Категория успешно удалена!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при удалении категории.".concat(e.getMessage()));
        }
        return "redirect:/category"; // Возвращает имя шаблона HTML для редактирования
    }
}
