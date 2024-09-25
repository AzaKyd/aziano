package com.stock.aziano.utils;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class TelegramNotificationService {
    private final String botToken = "7931158782:AAGQzJiBjI4PF-25o29UKMUNSfEuUya673c"; // Замените на ваш токен
    private final List<String> chatIds =List.of("709476803", "742638528"); // Список ID чатов

    private final DefaultAbsSender botSender;

    // Конструктор принимает список chatId
    public TelegramNotificationService() {
        DefaultBotOptions options = new DefaultBotOptions();
        this.botSender = new DefaultAbsSender(options) {
            @Override
            public String getBotToken() {
                return botToken;
            }
        };
    }

    public void sendSaleNotification(String saleAmount) {
        for (String chatId : chatIds) { // Проходим по списку ID чатов
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Успешная продажа! Сумма продажи: " + saleAmount + " KGS");

            try {
                botSender.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
