package ru.tinkoff.edu.java.scrapper.service.domain.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.domain.api.UpdateService;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JooqUpdateService implements UpdateService {
    private final JooqLinkRepository linkRepository;

    @Override
    @Transactional
    public List<LinkEntity> findLinksWithLastCheckedTimeLongAgo(Integer secondsDelta) {
        return linkRepository.findWithLastCheckedTimeLongAgo(secondsDelta);
    }

    @Override
    @Transactional
    public void updateLink(LinkEntity linkEntity, OffsetDateTime newUpdateTime) {
        linkRepository.updateLastUpdateTime(linkEntity.getId(), newUpdateTime);
    }
}
