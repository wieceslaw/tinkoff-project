package ru.tinkoff.edu.java.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.client.ScrapperWebClient;
import ru.tinkoff.edu.java.bot.client.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.client.dto.LinkResponse;
import ru.tinkoff.edu.java.bot.client.dto.ListLinksResponse;
import ru.tinkoff.edu.java.bot.client.dto.RemoveLinkRequest;

@RequiredArgsConstructor
@Service
public class ScrapperWebService {
    private final ScrapperWebClient client;

    void createChat(Long id) {
        client.createChat(id);
    }

    void deleteChat(Long id) {
        client.deleteChat(id);
    }

    LinkResponse createLink(Long id, String link) {
        return client.createLink(id, new AddLinkRequest(link));
    }

    ListLinksResponse getAllLinks(Long id) {
        return client.getAllLinks(id);
    }

    LinkResponse deleteLink(Long id, String link) {
        return client.deleteLink(id, new RemoveLinkRequest(link));
    }
}
