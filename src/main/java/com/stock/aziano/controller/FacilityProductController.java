package com.stock.aziano.controller;

import com.stock.aziano.dto.FacilityDto;
import com.stock.aziano.dto.FacilityProductDto;
import com.stock.aziano.models.FacilityProduct;
import com.stock.aziano.service.FacilityProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/facility-product")
public class FacilityProductController {

    @Autowired
    private FacilityProductService facilityProductService;

    @GetMapping("")
    public String getFacilityProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String barcode,
            @RequestParam(required = false) String productCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FacilityProductDto> productsPage = facilityProductService.getFilteredFacilityProducts(name, barcode, productCode, pageable);

        // Добавляем данные в модель для Thymeleaf
        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("currentPage", productsPage.getNumber());
        model.addAttribute("totalPages", productsPage.getTotalPages());
        model.addAttribute("totalItems", productsPage.getTotalElements());
        model.addAttribute("pageSize", productsPage.getSize());
//        model.addAttribute("name", name);  // Чтобы сохранить параметры поиска
//        model.addAttribute("barcode", barcode);
//        model.addAttribute("productCode", productCode);

        return "facility-product";  // Возвращаем название HTML-шаблона (products.html)
    }
}
