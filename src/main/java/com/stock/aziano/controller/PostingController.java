package com.stock.aziano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posting")
public class PostingController {

    @GetMapping("")
    public String posting() {
        return "posting";
    }

}
