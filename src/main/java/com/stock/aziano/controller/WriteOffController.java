package com.stock.aziano.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/writeoff")
public class WriteOffController {

    @GetMapping("")
    public String index() {

        return "writeoff";
    }
}
