package ru.tinkoff.edu.java.bot.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.java.bot.config.ApplicationConfig;

@Slf4j
@Component
public class TrackerBot extends TelegramLongPollingBot {
    private final ApplicationConfig config;
    private final MessageHandler messageHandler;

    public TrackerBot(ApplicationConfig config, MessageHandler messageHandler) {
        super(config.getBot().getToken());
        this.messageHandler = messageHandler;
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage response = messageHandler.handle(message);
            try {
                execute(response);
            } catch (TelegramApiException e) {
                log.error("Failed to send message due to error: {}", e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBot().getName();
    }
}
