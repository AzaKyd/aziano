package com.stock.aziano.utils;

import java.util.Random;

public class BarcodeGenerator {

    // Генерация случайного штрих-кода в формате EAN-13
    public static String generateBarcode() {
        Random random = new Random();
        StringBuilder barcode = new StringBuilder();

        // Генерируем 12 случайных цифр (EAN-13 требует 12 цифр + 1 контрольная)
        for (int i = 0; i < 12; i++) {
            barcode.append(random.nextInt(10));
        }

        // Добавляем контрольную цифру (checksum) для EAN-13
        barcode.append(generateCheckDigit(barcode.toString()));

        return barcode.toString();
    }

    // Метод для вычисления контрольной цифры EAN-13
    private static int generateCheckDigit(String barcodeWithoutCheckDigit) {
        int sum = 0;
        for (int i = 0; i < barcodeWithoutCheckDigit.length(); i++) {
            int digit = Character.getNumericValue(barcodeWithoutCheckDigit.charAt(i));
            if (i % 2 == 0) {
                sum += digit;
            } else {
                sum += digit * 3;
            }
        }
        int remainder = sum % 10;
        return remainder == 0 ? 0 : 10 - remainder;
    }
}