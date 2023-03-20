package ru.tinkoff.edu.java.scrapper.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "stackoverflow", ignoreUnknownFields = false)
@Configuration
public class StackOverflowProperties {
    private String url = "https://stackoverflow.com/2.3";
}
