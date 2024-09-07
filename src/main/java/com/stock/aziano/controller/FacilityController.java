package com.stock.aziano.controller;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.dto.WarehouseDto;
import com.stock.aziano.exception.DataIsUniqueException;
import com.stock.aziano.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/facility")
public class FacilityController {

    private final FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("")
    public String facilities(Model model) {
        List<WarehouseDto> warehouses = facilityService.getWarehouses();
        model.addAttribute("facilities", warehouses);
        return "facility";
    }

    @GetMapping("/add")
    public String addCategory() {
        return "/forms/facility/add-facility";
    }

    @PostMapping("/add")
    public String saveFacility(@ModelAttribute("facility") WarehouseDto warehouseDto, RedirectAttributes redirectAttributes) {
        try {
            facilityService.addWarehouse(warehouseDto);
            redirectAttributes.addFlashAttribute("message", "Склад успешно сохранен успешно сохранена!");
            return "redirect:/facility";
        } catch (DataIsUniqueException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/facility/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editFacility(@PathVariable("id") Long id, Model model) {
        WarehouseDto categoryDto = facilityService.getWarehouseById(id);
        model.addAttribute("facility", categoryDto);
        return "/forms/facility/edit-facility";
    }

    @PostMapping("/update")
    public String updateFacility( @ModelAttribute("warehouseDto") WarehouseDto warehouseDto,
                                  RedirectAttributes redirectAttributes) {
        try {
            facilityService.updateWarehouse(warehouseDto);
            redirectAttributes.addFlashAttribute("message", "Склад успешно обновлен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при обновлении склада.".concat(e.getMessage()));
        }
        return "redirect:/facility";
    }

    @GetMapping("/remove/{id}")
    public String removeFacility(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            facilityService.removeWarehouse(id);
            redirectAttributes.addFlashAttribute("message", "Склад успешно удален!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при удалении Склада.".concat(e.getMessage()));
        }
        return "redirect:/facility";
    }

}
