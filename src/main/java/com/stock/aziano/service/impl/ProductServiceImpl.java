package com.stock.aziano.service.impl;

import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.mappers.ProductMapper;
import com.stock.aziano.models.Product;
import com.stock.aziano.repository.ProductRepository;
import com.stock.aziano.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(ProductDto productDto) {

    }

    @Override
    public ProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public void updateProduct(ProductDto productDto) {

    }

    @Override
    public void removeProduct(Long id) {

    }
}
