package ru.tinkoff.edu.java.bot.telegram.command;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.edu.java.bot.service.ScrapperWebService;

@Order(5)
@Component
@RequiredArgsConstructor
public class UntrackCommand implements PublicCommand {
    private final ScrapperWebService webService;

    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "stop tracking link";
    }

    @Override
    public SendMessage handle(@NotNull Message message) {
        // TODO: implement
        return new SendMessage(message.getChatId().toString(), "Link is not tracking now!");
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith("/untrack");
    }
}
