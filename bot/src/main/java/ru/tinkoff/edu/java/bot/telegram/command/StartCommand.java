package ru.tinkoff.edu.java.bot.telegram.command;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.edu.java.bot.service.ScrapperWebService;

@Order(3)
@Component
@RequiredArgsConstructor
public class StartCommand implements Command {
    private final ScrapperWebService webService;

    @Override
    public SendMessage handle(@NotNull Message message) {
        webService.createChat(message.getChatId());
        // TODO: change welcome message
        return new SendMessage(message.getChatId().toString(), "Hello!");
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith("/start");
    }
}
