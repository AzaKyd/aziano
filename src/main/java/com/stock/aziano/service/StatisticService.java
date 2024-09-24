package com.stock.aziano.service;

import com.stock.aziano.dto.SalesReportDto;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {
    public List<SalesReportDto> getReport(LocalDate fromDate, LocalDate toDate);
}
