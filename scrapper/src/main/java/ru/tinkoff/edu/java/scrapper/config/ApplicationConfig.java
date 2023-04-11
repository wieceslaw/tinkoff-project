package ru.tinkoff.edu.java.scrapper.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;


@EnableScheduling
@Validated
@Data
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
public class ApplicationConfig {
    @NotNull
    private Scheduler scheduler;
    @NotNull
    private GitHub gitHub;
    @NotNull
    private StackOverflow stackOverflow;
    @NotNull
    private Bot bot;


    @Validated
    @Data
    public static class Scheduler {
        @NotNull
        private Duration interval;
        @NotNull
        private Integer checkSecondsDelay = 50000;
    }

    @Validated
    @Data
    public static class GitHub {
        @NotBlank
        private String url = "https://api.github.com";
    }

    @Validated
    @Data
    public static class StackOverflow {
        @NotBlank
        private String url = "https://stackoverflow.com/2.3";
    }


    @Validated
    @Data
    public static class Bot {
        @NotBlank
        private String url = "http://localhost";
    }
}
