package com.stock.aziano.service;

import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.dto.WarehouseDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getWarehouses();

    public void addWarehouse(ProductDto productDto);

    public ProductDto getWarehouseById(Long id);

    public void updateWarehouse(ProductDto productDto);

    public void removeWarehouse(Long id);
}
