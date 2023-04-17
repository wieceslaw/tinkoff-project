package ru.tinkoff.edu.java.scrapper.service.domain.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.exception.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
<<<<<<< HEAD
=======
import ru.tinkoff.edu.java.scrapper.exception.InternalError;
>>>>>>> 8140abd (resolved)
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqSubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.domain.api.SubscriptionService;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
<<<<<<< HEAD
=======
@Transactional(readOnly = true)
>>>>>>> 8140abd (resolved)
public class JooqSubscriptionService implements SubscriptionService {
    private final JooqLinkRepository linkRepository;
    private final JooqChatRepository chatRepository;
    private final JooqSubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public LinkEntity subscribe(Long chatId, URI url) {
<<<<<<< HEAD
        try {
            return linkRepository.subscribe(url.toString(), chatId);
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException("Subscription already exist", e);
=======
        LinkEntity linkEntity = linkRepository.find(url.toString());
        if (linkEntity != null) {
            subscriptionRepository.add(chatId, linkEntity.getId());
            return linkEntity;
        } else {
            log.info("Link does not exist yet");
            Long linkId = linkRepository.add(url.toString());
            log.info("New link id=" + linkId);
            log.info("New link=" + linkRepository.findById(linkId).toString());
            try {
                subscriptionRepository.add(linkId, chatId);
            } catch (DataAccessException e) {
                log.error(e.getMessage());
                throw new InternalError("Subscription already exist", e);
            }
            return linkRepository.findById(linkId);
>>>>>>> 8140abd (resolved)
        }
    }

    @Override
    @Transactional
    public LinkEntity unsubscribe(Long chatId, URI url) {
        LinkEntity linkEntity = linkRepository.find(url.toString());
        if (linkEntity != null) {
            subscriptionRepository.remove(chatId, linkEntity.getId());
            Integer subscriptions = subscriptionRepository.countSubscriptions(linkEntity.getId());
            if (subscriptions == 0) {
                linkRepository.removeById(linkEntity.getId());
            }
            return linkEntity;
        } else {
            log.error("Link not found");
<<<<<<< HEAD
            throw new IllegalArgumentException("Link not found");
=======
            throw new InternalError("Link not found");
>>>>>>> 8140abd (resolved)
        }
    }

    @Override
<<<<<<< HEAD
    @Transactional(readOnly = true)
=======
>>>>>>> 8140abd (resolved)
    public List<LinkEntity> getChatSubscriptions(Long chatId) {
        return linkRepository.findWithChatSubscription(chatId);
    }

    @Override
<<<<<<< HEAD
    @Transactional(readOnly = true)
=======
>>>>>>> 8140abd (resolved)
    public List<ChatEntity> getLinkSubscribers(Long linkId) {
        return chatRepository.findAllSubscribers(linkId);
    }
}
