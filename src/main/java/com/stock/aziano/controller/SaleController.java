package com.stock.aziano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaleController {
    @GetMapping("/sales")
    public String posting() {
        return "sales";
    }
}
