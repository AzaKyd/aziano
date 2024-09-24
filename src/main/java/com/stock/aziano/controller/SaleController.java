package com.stock.aziano.controller;

import com.stock.aziano.dto.FacilityDto;
import com.stock.aziano.dto.FacilityProductDto;
import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.dto.SaleDto;
import com.stock.aziano.exception.NotEnoughQuantityException;
import com.stock.aziano.mappers.FacilityProductMapper;
import com.stock.aziano.repository.FacilityProductRepository;
import com.stock.aziano.repository.FacilityRepository;
import com.stock.aziano.service.FacilityService;
import com.stock.aziano.service.ProductService;
import com.stock.aziano.service.SaleService;
import com.stock.aziano.service.impl.SaleServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private FacilityProductRepository facilityProductRepository;

    @Autowired
    private FacilityProductMapper facilityProductMapper;

    @Autowired
    private ProductService productService;


    @GetMapping("")
    public String index() {

        return "sales";
    }

    @PostMapping("")
    @Transactional
    public String sell(@ModelAttribute("sale") SaleDto saleDto, RedirectAttributes redirectAttributes) {

        FacilityProductDto facilityProductDto = facilityProductMapper.toDto(facilityProductRepository.
                findFacilityProductByProductId(saleDto.getProductId()));

        if(facilityProductDto.getQuantity() < saleDto.getQuantity()){
            redirectAttributes.addFlashAttribute("error", "Недостаточно товара на складе");
            return "redirect:/sales";
        }
        int result = facilityProductDto.getQuantity() - saleDto.getQuantity();

        if(result <= 0){
            facilityProductRepository.deleteFacilityProductById(facilityProductDto.getId());
        } else {
            facilityProductDto.setQuantity(result);
            facilityProductRepository.save(facilityProductMapper.toEntity(facilityProductDto));
        }
        ProductDto productDto = productService.getProductById(saleDto.getProductId());
        saleDto.setProduct(productDto);
        saleService.sell(saleDto);
        return "sales";
    }
}
