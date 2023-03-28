package ru.tinkoff.edu.java.bot.telegram.command;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class UnknownCommand implements Command {
    @Override
    public SendMessage handle(@NotNull Message message) {
        // TODO: implement
        return new SendMessage(message.getChatId().toString(),
                "Unknown command, use /help to get list of commands");
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return true;
    }
}
