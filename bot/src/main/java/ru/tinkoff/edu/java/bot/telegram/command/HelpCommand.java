package ru.tinkoff.edu.java.bot.telegram.command;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@Order(1)
public class HelpCommand implements PublicCommand {
    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "shows a list of commands";
    }

    @Override
    public SendMessage handle(@NotNull Message message) {
        // TODO: implement
        return new SendMessage(message.getChatId().toString(), "*your help is here*");
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith("/help");
    }
}
