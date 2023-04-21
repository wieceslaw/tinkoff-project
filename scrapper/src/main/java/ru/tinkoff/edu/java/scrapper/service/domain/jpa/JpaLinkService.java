package ru.tinkoff.edu.java.scrapper.service.domain.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.tinkoff.edu.java.scrapper.dto.model.Link;
import ru.tinkoff.edu.java.scrapper.service.domain.api.LinkService;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JpaLinkService implements LinkService {
    @Override
    public List<Link> updateLastCheckedTimeAndGet(Duration linkToBeCheckedInterval) {
        return null;
    }

    @Override
    public void updateLink(Link link, OffsetDateTime newUpdateTime) {

    }
}
