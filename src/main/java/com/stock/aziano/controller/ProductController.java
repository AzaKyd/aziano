package com.stock.aziano.controller;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.mappers.ProductMapper;
import com.stock.aziano.service.CategoryService;
import com.stock.aziano.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("")
    public String product(Model model) {
        List<ProductDto> products = productService.getProducts();
        model.addAttribute("products", products);
        return "products";
    }
}
