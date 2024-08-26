package com.stock.aziano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacilityController {

    @GetMapping("/facilities")
    public String facilities() {
        return "facilities";
    }
}
