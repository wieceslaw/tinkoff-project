package ru.tinkoff.edu.java.scrapper.service.api;

import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;

import java.net.URI;
import java.util.List;

public interface SubscriptionService {
    LinkEntity subscribe(Long chatId, URI url);
    LinkEntity unsubscribe(Long chatId, URI url);
    List<LinkEntity> getSubscriptions(Long chatId);
}
