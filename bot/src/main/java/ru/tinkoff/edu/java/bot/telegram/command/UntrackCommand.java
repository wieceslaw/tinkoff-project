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

    private static final String COMMAND = "/untrack";
    private static final String DESCRIPTION = "stop tracking link";
    private static final String SUCCESS_RESPONSE = "Removed link from your tacking list";

    @Override
    public String command() {
        return COMMAND;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

    @Override
    public SendMessage handle(@NotNull Message message) {
        // TODO: implement (check pattern /track <link>, then get link, then send to service)
        return new SendMessage(message.getChatId().toString(), SUCCESS_RESPONSE);
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith(COMMAND);
    }
}
