package ru.tinkoff.edu.java.scrapper.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "github", ignoreUnknownFields = false)
@Configuration
public class GitHubProperties {
    private String url = "https://api.github.com";
}
