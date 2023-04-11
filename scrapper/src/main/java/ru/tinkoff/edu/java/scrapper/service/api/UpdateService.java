package ru.tinkoff.edu.java.scrapper.service.api;

import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface UpdateService {
    List<LinkEntity> findLinksWithLastCheckedTimeLongAgo(Integer secondsDelta);
    void updateLink(LinkEntity linkEntity, OffsetDateTime newUpdateTime);
}
