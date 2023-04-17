package ru.tinkoff.edu.java.scrapper.service.domain.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkRepository;
<<<<<<< HEAD
import ru.tinkoff.edu.java.scrapper.service.domain.api.LinkService;

import java.time.Duration;
=======
import ru.tinkoff.edu.java.scrapper.service.domain.api.UpdateService;

>>>>>>> 8140abd (resolved)
import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
<<<<<<< HEAD
public class JooqUpdateService implements LinkService {
=======
@Transactional(readOnly = true)
public class JooqUpdateService implements UpdateService {
>>>>>>> 8140abd (resolved)
    private final JooqLinkRepository linkRepository;

    @Override
    @Transactional
<<<<<<< HEAD
    public List<LinkEntity> updateLastCheckedTimeAndGet(Duration linkToBeCheckedInterval) {
        return linkRepository.findWithLastCheckedTimeLongAgo(
                OffsetDateTime.now().plusNanos(linkToBeCheckedInterval.toNanos())
        );
=======
    public List<LinkEntity> findLinksWithLastCheckedTimeLongAgo(Integer secondsDelta) {
        return linkRepository.findWithLastCheckedTimeLongAgo(secondsDelta);
>>>>>>> 8140abd (resolved)
    }

    @Override
    @Transactional
    public void updateLink(LinkEntity linkEntity, OffsetDateTime newUpdateTime) {
        linkRepository.updateLastUpdateTime(linkEntity.getId(), newUpdateTime);
    }
}
