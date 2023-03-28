package ru.tinkoff.edu.java.bot.telegram.command;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.edu.java.bot.client.LinkResponse;
import ru.tinkoff.edu.java.bot.client.ListLinksResponse;
import ru.tinkoff.edu.java.bot.service.ScrapperWebService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Order(2)
@Component
@RequiredArgsConstructor
public class ListCommand implements PublicCommand {
    private final ScrapperWebService webService;

    private static final String EMPTY_LINKS_LIST_MESSAGE =
            "You don't have tracked links yet, use /track <link> to track one";
    private static final String COMMAND = "/list";
    private static final String DESCRIPTION = "show a list of tracked links";

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
        ListLinksResponse response = webService.getAllLinks(message.getChatId());
        String text = response.size() == 0 ? EMPTY_LINKS_LIST_MESSAGE : getFormattedText(response);
        return new SendMessage(message.getChatId().toString(), text);
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith(COMMAND);
    }

    private String getFormattedText(ListLinksResponse response) {
        List<String> links = response
                .links()
                .stream()
                .map(LinkResponse::link)
                .map(URI::toString)
                .toList();
        return "List of your current tracked links: \n" + links
                .stream()
                .map(link -> "- " + link)
                .collect(Collectors.joining("\n"));
    }
}
