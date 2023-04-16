package ru.tinkoff.edu.java.scrapper.service.api;

import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface LinkService {
    List<LinkEntity> updateLastCheckedTimeAndGet(Integer numberOfLinks);
    void updateLink(LinkEntity linkEntity, OffsetDateTime newUpdateTime);
}
