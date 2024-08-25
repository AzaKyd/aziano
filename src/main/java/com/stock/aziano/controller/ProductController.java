package com.stock.aziano.controller;

import com.stock.aziano.dto.CategoryDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @GetMapping("/products")
    public String product(Model model) {

        return "products";
    }
}
