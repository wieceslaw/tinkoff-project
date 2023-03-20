package ru.tinkoff.edu.java.scrapper.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;


@EnableScheduling
@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Configuration
public class ApplicationConfig {
    @NotNull String test;
    @NotNull Scheduler scheduler;
}
