package com.stock.aziano.controller;

import com.stock.aziano.dto.*;
import com.stock.aziano.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posting")
public class PostingController {


    private final ProductService productService;
    private final PostingService postingService;
    private final FacilityService facilityService;
    private final CategoryService categoryService;
    private final PostingProductService postingProductService;

    @Autowired
    public PostingController(ProductService productService,
                             CategoryService categoryService,
                             FacilityService facilityService,
                             PostingProductService postingProductService,
                             PostingService postingService
    )
    {
        this.productService = productService;
        this.categoryService = categoryService;
        this.facilityService = facilityService;
        this.postingProductService = postingProductService;
        this.postingService = postingService;
    }

    @GetMapping("")
    public String posting(Model model) {
        model.addAttribute("postings", postingService.getPostings());
        return "posting";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        List<ProductDto> productsDto = productService.getProducts();
        List<FacilityDto> facilitiesDto = facilityService.getFacilities();
        model.addAttribute("products", productsDto);
        model.addAttribute("facilities", facilitiesDto);
        return "/forms/posting/add";
    }

    @PostMapping("/add")
    public String addReceipt(
            @ModelAttribute PostingDto postingDto,
            @ModelAttribute PostingProductListDto postingProductListDto
    ) {

        List<PostingProductDto> items = postingProductListDto.getItems();
        List<PostingProductDto> postingProductDto = new ArrayList<>();

        for (PostingProductDto item : items) {
            if (item.getProduct() != null && item.getQuantity() != null) {
                postingProductDto.add(item);
            }
        }

        postingService.addPosting(postingDto, postingProductDto);

        return "redirect:/posting"; // Перенаправление после сохранения
    }

    public class PostingProductListDto {
        private List<PostingProductDto> items;

        public List<PostingProductDto> getItems() {
            return items;
        }

        public void setItems(List<PostingProductDto> items) {
            this.items = items;
        }
    }
}

