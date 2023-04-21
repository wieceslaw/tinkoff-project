package ru.tinkoff.edu.java.scrapper.service.domain.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.domain.api.LinkService;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JooqUpdateService implements LinkService {
    private final JooqLinkRepository linkRepository;
    @Override
    @Transactional
    public List<Link> updateLastCheckedTimeAndGet(Duration linkToBeCheckedInterval) {
        return linkRepository.findWithLastCheckedTimeLongAgo(
                OffsetDateTime.now().plusNanos(linkToBeCheckedInterval.toNanos())
        );
    }

    @Override
    @Transactional
    public void updateLink(Link link, OffsetDateTime newUpdateTime) {
        linkRepository.updateLastUpdateTime(link.getId(), newUpdateTime);
    }
}
