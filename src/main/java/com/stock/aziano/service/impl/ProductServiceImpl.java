package com.stock.aziano.service.impl;

import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.exception.DataIsUniqueException;
import com.stock.aziano.exception.ResourceNotFoundException;
import com.stock.aziano.mappers.ProductMapper;
import com.stock.aziano.models.Product;
import com.stock.aziano.repository.CategoryRepository;
import com.stock.aziano.repository.ProductRepository;
import com.stock.aziano.repository.ProductSpecification;
import com.stock.aziano.service.ProductService;
import com.stock.aziano.utils.ImageUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository,
                       ProductMapper productMapper,
                       CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductDto> getProductsPage(String productCode, String barcode, String name, Pageable pageable) {
        // Создаем спецификацию для фильтрации
        Specification<Product> spec = Specification
                .where(ProductSpecification.hasProductName(name))
                .and(ProductSpecification.hasBarcode(barcode))
                .and(ProductSpecification.hasProductCode(productCode));

        // Получаем страницу продуктов из репозитория по спецификации
        Page<Product> products = productRepository.findAll(spec, pageable);

        // Преобразуем каждый продукт в DTO и возвращаем результат
        return products.map(this::convertToDto);
    }

    public ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setProductCode(product.getProductCode());
        dto.setBarcode(product.getBarcode());
        dto.setName(product.getName());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());
        dto.setCostPrice(product.getCostPrice());
        dto.setSellingPrice(product.getSellingPrice());
        dto.setImageUrl(product.getImageUrl());
        dto.setDescription(product.getDescription());
        return dto;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> {
                    ProductDto productDto = productMapper.toDto(product);
                    categoryRepository.findById(product.getCategory().getId())
                            .ifPresent(category -> productDto.setCategoryName(category.getName()));
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(ProductDto productDto) {
        if(productRepository.existsByName(productDto.getName())) {
            throw new DataIsUniqueException("Товар с таким именем уже существует");
        }
        productRepository.save(productMapper.toEntity(productDto));
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        // Проверяем, присутствует ли значение
        if (product.isPresent()) {
            return productMapper.toDto(product.get());
        } else {
            throw new ResourceNotFoundException("Товар не найден");
        }
    }

    @Override
    public void updateProduct(ProductDto productDto, MultipartFile imageFile) throws IOException {
        // Ищем существующий продукт в базе данных по ID
        Optional<Product> existingProduct = productRepository.findById(productDto.getId());

        if (existingProduct.isPresent()) {
            // Преобразуем DTO в сущность и обновляем существующий продукт
            String oldImageUrl = existingProduct.get().getImageUrl();

            Product updatedProduct = productMapper.toEntity(productDto);
            if(imageFile.isEmpty()) {
               updatedProduct.setImageUrl(oldImageUrl);
            } else {
                try {
                    String projectPath = new File(".").getCanonicalPath();

                    Path filePath = Paths.get(projectPath + "/src/main/resources/static".concat(oldImageUrl));

                    Files.delete(filePath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                String imagePath = ImageUtils.saveImage(imageFile);
                updatedProduct.setImageUrl(imagePath);
            }
            updatedProduct.setBarcode(existingProduct.get().getBarcode());
            updatedProduct.setProductCode(existingProduct.get().getProductCode());
            updatedProduct.setId(existingProduct.get().getId());

            // Обновляем продукт
            productRepository.save(updatedProduct);
        } else {
            throw new EntityNotFoundException("Продукт с ID " + productDto.getId() + " не найден.");
        }
    }

    @Override
    public void removeProduct(Long id) {


        productRepository.deleteById(id);

    }
}
