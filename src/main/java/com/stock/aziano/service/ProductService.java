package com.stock.aziano.service;

import com.stock.aziano.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public List<ProductDto> getProducts();

    public void addProduct(ProductDto productDto);

    public ProductDto getProductById(Long id);

    public ProductDto getProductByBarcode(String barcode);

    public ProductDto getProductBy(String productCode);

    public void updateProduct(ProductDto productDto, MultipartFile imageFile) throws IOException;

    public void removeProduct(Long id);

    Page<ProductDto> getProductsPage(String productCode, String barcode, String name, Pageable pageable);
}
