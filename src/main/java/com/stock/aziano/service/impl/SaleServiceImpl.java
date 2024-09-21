package com.stock.aziano.service.impl;

import com.stock.aziano.dto.SaleDto;
import com.stock.aziano.mappers.SaleMapper;
import com.stock.aziano.repository.SaleRepository;
import com.stock.aziano.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleMapper SaleMapper;


    @Override
    public List<SaleDto> getSales() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Bishkek"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Указываем нужный формат
        String formattedDate = today.format(formatter);
        return saleRepository.findAllBySaleDate(LocalDate.now(ZoneId.of("Asia/Bishkek"))).
                stream().
                map(SaleMapper::toDto).
                toList();
    }

    @Override
    public void addSale(SaleDto saleDto) {

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
