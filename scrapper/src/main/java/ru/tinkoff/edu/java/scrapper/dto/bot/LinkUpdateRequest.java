package ru.tinkoff.edu.java.scrapper.dto.bot;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record LinkUpdateRequest(
    @Positive
    Long id,
    @NotBlank
    String url,
    @NotBlank
    String description,
    @NotNull
    List<Long> tgChatsIds
) {
}
