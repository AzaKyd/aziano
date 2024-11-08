package com.stock.aziano.controller;

import com.stock.aziano.dto.CategoryDto;
import com.stock.aziano.dto.ProductDto;
import com.stock.aziano.exception.DataIsUniqueException;
import com.stock.aziano.exception.ResourceNotFoundException;
import com.stock.aziano.mappers.ProductMapper;
import com.stock.aziano.service.CategoryService;
import com.stock.aziano.service.ProductService;
import com.stock.aziano.utils.BarcodeGenerator;
import com.stock.aziano.utils.ImageUtils;
import com.stock.aziano.utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService)
    {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String product(@RequestParam(value = "productCode", required = false) String productCode,
                          @RequestParam(value = "barcode", required = false) String barcode,
                          @RequestParam(value = "name", required = false) String name,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "100") int size,
                          Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<ProductDto> productPage = productService.getProductsPage(productCode, barcode, name, pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "products";
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getProducts(); // Получение списка всех товаров из базы данных
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product-price")
    public ResponseEntity<Map<String, Object>> getProductPrice(@RequestParam Long productId) {
        BigDecimal price = productService.getProductById(productId).getSellingPrice();

        Map<String, Object> response = new HashMap<>();
        response.put("price", price);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.ok().headers(headers).body(response);
    }

    @GetMapping("/productcode")
    public ResponseEntity<Map<String, Object>> getProductByProductCode(@RequestParam String productCode) {
        Map<String, Object> response = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ProductDto productDto = productService.getProductBy(productCode);
            response.put("sellingPrice", productDto.getSellingPrice());
            response.put("costPrice", productDto.getCostPrice());
            response.put("name", productDto.getName());
            response.put("barcode", productDto.getBarcode());
            response.put("id", productDto.getId());
            return ResponseEntity.ok().headers(headers).body(response);
        } catch (ResourceNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        } catch (Exception e) {
            response.put("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(response);
        }
    }

    @GetMapping("/barcode")
    public ResponseEntity<Map<String, Object>> getProductByBarcode(@RequestParam String barcode) {
        Map<String, Object> response = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ProductDto productDto = productService.getProductByBarcode(barcode);
            response.put("sellingPrice", productDto.getSellingPrice());
            response.put("costPrice", productDto.getCostPrice());
            response.put("name", productDto.getName());
            response.put("productCode", productDto.getProductCode());
            response.put("id", productDto.getId());
            return ResponseEntity.ok().headers(headers).body(response);
        } catch (ResourceNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        } catch (Exception e) {
            response.put("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(response);
        }
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "/forms/product/add-product";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute("facility") ProductDto productDto,
                              @RequestParam("image") MultipartFile imageFile,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        try {
            // Сохраняем изображение
            if (imageFile.isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "Ошибка, Файл изображения пуст");
                return "redirect:/products/add";
            }

            if (productDto.getProductCode() == null || productDto.getProductCode().isEmpty()) {
                productDto.setProductCode(String.valueOf((int) (Math.random() * 100000)));
            }

            if (productDto.getBarcode() == null || productDto.getBarcode().isEmpty()) {
                productDto.setBarcode(BarcodeGenerator.generateBarcode());
            }

            String imagePath = ImageUtils.saveImage(imageFile);
            productDto.setImageUrl(imagePath);
            productService.addProduct(productDto);

            redirectAttributes.addFlashAttribute("message", "Товар успешно сохранен!");
            return "redirect:/products";
        } catch (DataIsUniqueException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/products/add";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        ProductDto productDto = productService.getProductById(id);
        List<CategoryDto> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("product", productDto);
        return "/forms/product/edit-product";
    }

    @PostMapping("/update")
    public String updateProduct( @ModelAttribute("productDto") ProductDto productDto,
                                  @RequestParam("image") MultipartFile imageFile,
                                  RedirectAttributes redirectAttributes) {


        try {
            productService.updateProduct(productDto, imageFile);
            redirectAttributes.addFlashAttribute("message", "Товар успешно обновлен!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при обновлении товара.".concat(e.getMessage()));
        }
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {

            ProductDto productDto = productService.getProductById(id);
            String imageUrl = productDto.getImageUrl();

            productService.removeProduct(id);
            try {
                // Путь к папке проекта
                String projectPath = new File(".").getCanonicalPath();

                // Полный путь к файлу
                Path filePath = Paths.get(projectPath + "/src/main/resources/static" + imageUrl);

                // Удаляем файл
                Files.delete(filePath);
                System.out.println("Файл успешно удалён: " + filePath.toString());

            } catch (IOException e) {
                System.out.println("Ошибка при удалении файла: " + e.getMessage());
                e.printStackTrace();
            }
            redirectAttributes.addFlashAttribute("message", "Товар успешно удален!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Ошибка при удалении товара.".concat(e.getMessage()));
        }
        return "redirect:/products";
    }

    @GetMapping("/download-pdf/{id}")
    public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable("id") Long id) throws IOException {
        // Получаем данные товара по ID
        ProductDto product = productService.getProductById(id);

        // Генерация PDF с помощью утилитного класса
        ByteArrayOutputStream byteArrayOutputStream = PdfGenerator.generateProductPdf(product);

        // Подготавливаем ResponseEntity для отправки PDF
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=product_" + product.getId() + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }
}
