package ru.tinkoff.edu.java.scrapper.service.domain.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.edu.java.scrapper.dto.model.Chat;
import ru.tinkoff.edu.java.scrapper.dto.model.Link;
import ru.tinkoff.edu.java.scrapper.service.domain.api.SubscriptionService;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JpaSubscriptionService implements SubscriptionService {
    @Override
    public Link subscribe(Long chatId, URI url) {
        return null;
    }

    @Override
    public Link unsubscribe(Long chatId, URI url) {
        return null;
    }

    @Override
    public List<Link> getChatSubscriptions(Long chatId) {
        return null;
    }

    @Override
    public List<Chat> getLinkSubscribers(Long linkId) {
        return null;
    }
}
