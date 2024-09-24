package com.stock.aziano.controller;

import com.stock.aziano.dto.SaleDto;
import com.stock.aziano.dto.SalesReportDto;
import com.stock.aziano.mappers.FacilityProductMapper;
import com.stock.aziano.repository.FacilityProductRepository;
import com.stock.aziano.service.ProductService;
import com.stock.aziano.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/statistic")
public class StatisticController {


    @Autowired
    private StatisticService statisticService;


    @GetMapping("")
    public String index(@RequestParam(required = false) LocalDate fromDate,
                        @RequestParam(required = false) LocalDate toDate,
                        Model model) {
        // Если fromDate или toDate равны null, устанавливаем значения по умолчанию
        if (fromDate == null) {
            fromDate = LocalDate.now(); // по умолчанию сегодня
        }

        if (toDate == null) {
            toDate = LocalDate.now(); // по умолчанию сегодня
        }

        List<SalesReportDto> salesReportDto = statisticService.getReport(fromDate, toDate);
        model.addAttribute("reports", salesReportDto);

        return "statistic";
    }
}
