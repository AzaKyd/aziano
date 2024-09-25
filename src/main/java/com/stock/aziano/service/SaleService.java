package com.stock.aziano.service;

import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.dto.SaleDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface SaleService {
    public List<SaleDto> getSales(LocalDate date);

    public void sell(SaleDto saleDto);

    public SaleDto getSaleById(Long id);

    public void updateSale(SaleDto saleDto);

    public void removeSale(Long id);
}
