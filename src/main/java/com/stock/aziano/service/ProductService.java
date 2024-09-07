package com.stock.aziano.service;

import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.dto.WarehouseDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getProducts();

    public void addProduct(ProductDto productDto);

    public ProductDto getProductById(Long id);

    public void updateProduct(ProductDto productDto);

    public void removeProduct(Long id);
}
