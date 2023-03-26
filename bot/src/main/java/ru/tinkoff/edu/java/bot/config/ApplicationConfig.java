package ru.tinkoff.edu.java.bot.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
public class ApplicationConfig {
    @NotNull
    private String test;
    @NotNull
    private Bot bot;
    @NotNull
    private Scrapper scrapper;

    @Validated
    @Getter
    @Setter
    public static class Bot {
        @NotNull
        private String token;
    }

    @Validated
    @Getter
    @Setter
    public static class Scrapper {
        @NotNull
        private String url;
    }
}