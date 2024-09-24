package com.stock.aziano.service.impl;

import com.stock.aziano.dto.SalesReportDto;
import com.stock.aziano.models.Sale;
import com.stock.aziano.repository.SaleRepository;
import com.stock.aziano.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<SalesReportDto> getReport(LocalDate fromDate, LocalDate toDate) {

        List<Sale> sales = saleRepository.findSalesForPeriod(fromDate.atStartOfDay(), toDate.atTime(23, 59, 59));

        // Сгруппируем продажи по дате
        Map<LocalDate, List<Sale>> salesGroupedByDate = sales.stream()
                .collect(Collectors.groupingBy(sale -> sale.getSaleDate().toLocalDate()));

        // Формируем список отчетов
        List<SalesReportDto> report = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Sale>> entry : salesGroupedByDate.entrySet()) {
            LocalDate date = entry.getKey();
            List<Sale> dailySales = entry.getValue();

            BigDecimal totalCostPrice = dailySales.stream()
                    .map(Sale::getCostPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal totalSellingPrice = dailySales.stream()
                    .map(Sale::getSellingPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal netProfit = totalSellingPrice.subtract(totalCostPrice);

            SalesReportDto salesReportDto = new SalesReportDto();
            salesReportDto.setSaleDate(date);
            salesReportDto.setTotalCostPrice(totalCostPrice);
            salesReportDto.setTotalSellingPrice(totalSellingPrice);
            salesReportDto.setNetProfit(netProfit);

            report.add(salesReportDto);
        }

        return report;
    }
}
