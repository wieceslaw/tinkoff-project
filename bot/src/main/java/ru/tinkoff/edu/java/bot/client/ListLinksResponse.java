package ru.tinkoff.edu.java.bot.client;

import java.util.List;

public record ListLinksResponse(
        List<LinkResponse> links,
        Integer size
) {
}
