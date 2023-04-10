package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.exception.InternalError;
import ru.tinkoff.edu.java.scrapper.repository.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.JdbcSubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.api.SubscriptionService;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JdbcSubscriptionService implements SubscriptionService {
    private final JdbcSubscriptionRepository subscriptionRepository;
    private final JdbcLinkRepository linkRepository;

    @Override
    @Transactional
    public LinkEntity subscribe(Long chatId, URI url) {
        try {
            LinkEntity linkEntity = linkRepository.find(url.toString());
            subscriptionRepository.add(chatId, linkEntity.getId());
            return linkEntity;
        } catch (EmptyResultDataAccessException ignored) {
            Long linkId = linkRepository.add(url.toString());
            try {
                subscriptionRepository.add(chatId, linkId);
            } catch (DuplicateKeyException e) {
                // TODO?: change exception
                log.error(e.getMessage());
                throw new InternalError("Subscription already exist", e);
            }
            return linkRepository.findById(linkId);
        }
    }

    @Override
    @Transactional
    public LinkEntity unsubscribe(Long chatId, URI url) {
        try {
            LinkEntity linkEntity = linkRepository.find(url.toString());
            subscriptionRepository.remove(chatId, linkEntity.getId());
            Integer subscriptions = subscriptionRepository.countSubscriptions(linkEntity.getId());
            if (subscriptions == 0) {
                linkRepository.removeById(linkEntity.getId());
            }
            return linkEntity;
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            // TODO?: change exception
            throw new InternalError("Link not found", e);
        }
    }

    @Override
    public List<LinkEntity> getSubscriptions(Long chatId) {
        // TODO?: check if chat exists
        return linkRepository.findWithChatSubscription(chatId);
    }
}
