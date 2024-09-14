package com.stock.aziano.utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtils {

    private static final String UPLOAD_DIR = "src/main/resources/static/assets/upload/";

    public static String saveImage(MultipartFile file) throws IOException {
        // Проверка директории
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Сохранение файла
        String fileName = UUID.randomUUID().toString().concat(".jpg");
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), filePath);

        return "/assets/upload/" + fileName; // Возвращаем путь к изображению
    }
}