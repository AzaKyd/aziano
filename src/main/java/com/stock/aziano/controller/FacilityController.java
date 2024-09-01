package com.stock.aziano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacilityController {

    @GetMapping("/facility")
    public String facilities() {
        return "facility";
    }
}
