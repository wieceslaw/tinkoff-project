package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.repository.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.api.UpdateService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JdbcUpdateService implements UpdateService {
    private final JdbcLinkRepository linkRepository;

    @Override
    @Transactional
    public List<LinkEntity> findLinksWithLastCheckedTimeLongAgo(Integer secondsDelta) {
        return linkRepository.findWithLastCheckedTimeLongAgo(secondsDelta);
    }
}
