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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String product(Model model) {
        List<ProductDto> products = productService.getProducts();
        model.addAttribute("products", products);
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
}
