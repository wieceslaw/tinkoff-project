package ru.tinkoff.edu.java.scrapper.service.domain.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import ru.tinkoff.edu.java.scrapper.dto.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.domain.api.ChatService;


@Slf4j
@RequiredArgsConstructor
public class JpaChatService implements ChatService {
    private final JpaChatRepository chatRepository;
    private final JpaLinkRepository linkRepository;

    @Override
    public void register(Long id) {
        try {
            chatRepository.saveAndFlush(new ChatEntity(id));
        } catch (DuplicateKeyException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException("Chat already registered", e);
        }
    }

    @Override
    public void unregister(Long id) {
        chatRepository.deleteById(id);
        linkRepository.deleteWithZeroSubscribers();
    }
}
