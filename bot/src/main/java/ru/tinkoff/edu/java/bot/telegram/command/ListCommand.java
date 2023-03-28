package ru.tinkoff.edu.java.bot.telegram.command;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.edu.java.bot.client.LinkResponse;
import ru.tinkoff.edu.java.bot.service.ScrapperWebService;

import java.util.List;

@Order(2)
@Component
@RequiredArgsConstructor
public class ListCommand implements PublicCommand {
    private final ScrapperWebService webService;

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "show a list of tracked links";
    }

    @Override
    public SendMessage handle(@NotNull Message message) {
        List<String> links = webService.getAllLinks(message.getChatId())
                .links()
                .stream()
                .map(LinkResponse::url).toList();
        // TODO: fix
        return new SendMessage(message.getChatId().toString(), "*your list is here*" + links);
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith("/list");
    }
}
