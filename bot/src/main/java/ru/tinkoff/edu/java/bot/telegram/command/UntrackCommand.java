package ru.tinkoff.edu.java.bot.telegram.command;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tinkoff.edu.java.bot.service.ScrapperWebService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Order(5)
@Slf4j
@Component
@RequiredArgsConstructor
public class UntrackCommand implements PublicCommand {
    private final ScrapperWebService webService;

    private static final Pattern pattern = Pattern.compile("^\\s*/untrack (\\S+)\\s*$");
    private static final String COMMAND = "/untrack";
    private static final String DESCRIPTION = "stop tracking link";
    private static final String SUCCESS_RESPONSE = "Removed link from your tacking list";
    private static final String WRONG_FORMAT_RESPONSE = "Use correct format: '\\untrack <link>'";

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
        String text = message.getText();
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return new SendMessage(message.getChatId().toString(), WRONG_FORMAT_RESPONSE);
        }
        String url = matcher.group(1);
        log.info("Removed link {}", url);
        webService.createLink(message.getChatId(), url);
        return new SendMessage(message.getChatId().toString(), SUCCESS_RESPONSE);
    }

    @Override
    public boolean supports(@NotNull Message message) {
        return message.getText().trim().startsWith(COMMAND);
    }
}
