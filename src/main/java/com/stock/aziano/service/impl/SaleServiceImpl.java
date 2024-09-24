package com.stock.aziano.service.impl;

import com.stock.aziano.dto.SaleDto;
import com.stock.aziano.mappers.SaleMapper;
import com.stock.aziano.repository.SaleRepository;
import com.stock.aziano.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleMapper saleMapper;


    @Override
    public List<SaleDto> getSales(LocalDate today) {
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        return saleRepository.findSalesForDay(startOfDay, endOfDay).
                stream().
                map(saleMapper::toDto).
                toList();
    }

    @Override
    public void sell(SaleDto saleDto) {
        saleRepository.save(saleMapper.toEntity(saleDto));
    }

    @Override
    public SaleDto getSaleById(Long id) {
        return null;
    }

    @Override
    public void updateSale(SaleDto saleDto) {

    }

    @Override
    public void removeSale(Long id) {

    }
}
