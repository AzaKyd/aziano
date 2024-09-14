package com.stock.aziano.controller;

import com.stock.aziano.dto.FacilityDto;
import com.stock.aziano.exception.DataIsUniqueException;
import com.stock.aziano.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<FacilityDto> facilities = facilityService.getFacilities();
        model.addAttribute("facilities", facilities);
        return "facility";
    }

    @GetMapping("/add")
    public String addCategory() {
        return "/forms/facility/add-facility";
    }

    @PostMapping("/add")
    public String saveFacility(@ModelAttribute("facility") FacilityDto facilityDto, RedirectAttributes redirectAttributes) {
        try {
            facilityService.addFacility(facilityDto);
            redirectAttributes.addFlashAttribute("message", "Склад успешно сохранен успешно сохранена!");
            return "redirect:/facility";
        } catch (DataIsUniqueException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/facility/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editFacility(@PathVariable("id") Long id, Model model) {
        FacilityDto categoryDto = facilityService.getFacilityById(id);
        model.addAttribute("facility", categoryDto);
        return "/forms/facility/edit-facility";
    }

    @PostMapping("/update")
    public String updateFacility( @ModelAttribute("warehouseDto") FacilityDto facilityDto,
                                  RedirectAttributes redirectAttributes) {
        try {
            facilityService.updateFacility(facilityDto);
            redirectAttributes.addFlashAttribute("message", "Склад успешно обновлен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при обновлении склада.".concat(e.getMessage()));
        }
        return "redirect:/facility";
    }

    @GetMapping("/remove/{id}")
    public String removeFacility(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            facilityService.removeFacility(id);
            redirectAttributes.addFlashAttribute("message", "Склад успешно удален!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при удалении Склада.".concat(e.getMessage()));
        }
        return "redirect:/facility";
    }

}
