package com.stock.aziano.service;

import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.dto.WarehouseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    public List<ProductDto> getProducts();

    public void addProduct(ProductDto productDto);

    public ProductDto getProductById(Long id);

    public void updateProduct(ProductDto productDto, MultipartFile imageFile) throws IOException;

    public void removeProduct(Long id);
}
