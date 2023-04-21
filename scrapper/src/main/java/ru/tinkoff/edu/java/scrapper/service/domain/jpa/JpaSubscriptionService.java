package ru.tinkoff.edu.java.scrapper.service.domain.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.dto.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.dto.entity.SubscriptionEntity;
import ru.tinkoff.edu.java.scrapper.dto.entity.SubscriptionPk;
import ru.tinkoff.edu.java.scrapper.dto.model.Chat;
import ru.tinkoff.edu.java.scrapper.dto.model.Link;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaSubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.domain.api.SubscriptionService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JpaSubscriptionService implements SubscriptionService {
    private final JpaSubscriptionRepository subscriptionRepository;
    private final JpaLinkRepository linkRepository;
    private final JpaChatRepository chatRepository;

    @Override
    @Transactional
    public Link subscribe(Long chatId, URI url) {
        if (!chatRepository.existsById(chatId)) {
            throw new IllegalArgumentException("Chat does not exist");
        }
        Optional<LinkEntity> linkEntityOptional = linkRepository.findLinkEntityByUrl(url.toString());
        LinkEntity linkEntity = linkEntityOptional.orElseGet(
                () -> linkRepository.saveAndFlush(new LinkEntity(url.toString()))
        );
        subscriptionRepository.saveAndFlush(new SubscriptionEntity(chatId, linkEntity.getId()));
        return new Link(
                linkEntity.getId(),
                linkEntity.getUrl(),
                linkEntity.getLastCheckTime(),
                linkEntity.getLastUpdateTime()
        );
    }

    @Override
    @Transactional
    public Link unsubscribe(Long chatId, URI url) {
        if (!chatRepository.existsById(chatId)) {
            throw new IllegalArgumentException("Chat does not exist");
        }
        Optional<LinkEntity> linkEntityOptional = linkRepository.findLinkEntityByUrl(url.toString());
        if (linkEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("Link does not exist");
        }
        LinkEntity linkEntity = linkEntityOptional.get();
        subscriptionRepository.deleteById(new SubscriptionPk(chatId, linkEntity.getId()));
        return new Link(
                linkEntity.getId(),
                linkEntity.getUrl(),
                linkEntity.getLastCheckTime(),
                linkEntity.getLastUpdateTime()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> getChatSubscriptions(Long chatId) {
        log.info("Get chat subscriptions");
        Optional<ChatEntity> chatEntityOptional = chatRepository.findById(chatId);
        if (chatEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("Chat does not exist");
        }
        ChatEntity chatEntity = chatEntityOptional.get();
        return chatEntity.getSubscriptions().stream()
                .map(link -> new Link(
                        link.getId(),
                        link.getUrl(),
                        link.getLastCheckTime(),
                        link.getLastUpdateTime()
                ))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chat> getLinkSubscribers(Long linkId) {
        Optional<LinkEntity> linkEntityOptional = linkRepository.findById(linkId);
        if (linkEntityOptional.isEmpty()) {
            throw new IllegalArgumentException("Link does not exist");
        }
        LinkEntity linkEntity = linkEntityOptional.get();
        return linkEntity.getSubscribers().stream()
                .map(chat -> new Chat(chat.getId()))
                .toList();
    }
}
