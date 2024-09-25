package com.stock.aziano.utils;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.stock.aziano.dto.ProductDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerator {

    public static ByteArrayOutputStream generateProductPdf(ProductDto product) throws IOException {
// Переводим миллиметры в пункты для размеров 58 мм на 40 мм
        float width = 58 * 2.83465f;
        float height = 40 * 2.83465f;

// Генерация PDF в ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);

// Устанавливаем размер страницы 58x40 мм
        pdfDoc.setDefaultPageSize(new PageSize(width, height));

        Document document = new Document(pdfDoc);

// Настройка шрифта для работы с кириллицей
        PdfFont font1251 = PdfFontFactory.createFont("src/main/resources/static/assets/fonts/arial.ttf", "Cp1251", PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
        document.setFont(font1251);

// Уменьшаем размер шрифта
        document.setFontSize(9);
        document.setMargins(5, 5, 5, 5); // top, right, bottom, left
// Выравнивание текста

// Generate the barcode
        String barcodeData = product.getBarcode(); // Get the barcode data
        Barcode128 barcode128 = new Barcode128(pdfDoc);
        barcode128.setCode(barcodeData);

// Create an image from the barcode
        Image barcodeImage = new Image(barcode128.createFormXObject(pdfDoc));
        barcodeImage.setHeight(36); // Set the height of the barcode image
        barcodeImage.setWidth(144); // Set the width of the barcode image

// Add the barcode image to the document



// Добавляем информацию в PDF
        document.add(new Paragraph(product.getName()).setMarginBottom(0));
        document.add(new Paragraph("Код: " + product.getProductCode()).setMarginBottom(0));
        document.add(new Paragraph("Цена: " + product.getSellingPrice().toString()).setMarginBottom(0));
        document.add(barcodeImage);


// Закрываем документ
        document.close();

        return byteArrayOutputStream;


    }

}
