package ru.tinkoff.edu.java.bot.client;

import jakarta.validation.constraints.NotBlank;

public record AddLinkRequest(
        @NotBlank String link
) {
}
