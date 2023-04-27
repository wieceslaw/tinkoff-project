package ru.tinkoff.edu.java.bot.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.annotation.Validated;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;
import ru.tinkoff.edu.java.parser.ParserConfig;

@Validated
@Data
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
@Import({
        TelegramBotStarterConfiguration.class,
        ParserConfig.class
})
public class ApplicationConfig {
    @NotNull
    private Bot bot;
    @NotNull
    private Scrapper scrapper;
    @NotNull
    private ScrapperQueue scrapperQueue;

    @Validated
    @Data
    public static class Bot {
        @NotBlank
        private String token;
        @NotBlank
        private String name;
    }

    @Validated
    @Data
    public static class Scrapper {
        @NotBlank
        private String url;
    }

    @Validated
    @Data
    public static class ScrapperQueue {
        @NotBlank
        private String name;
    }
}