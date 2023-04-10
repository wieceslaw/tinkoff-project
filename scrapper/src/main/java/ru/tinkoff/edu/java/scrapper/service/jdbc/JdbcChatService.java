package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.exception.InternalError;
import ru.tinkoff.edu.java.scrapper.repository.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.api.ChatService;

@Service
@Slf4j
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {
    private final JdbcChatRepository chatRepository;
    private final JdbcLinkRepository linkRepository;

    @Override
    public void register(Long id) {
        try {
            chatRepository.add(id);
        } catch (DuplicateKeyException e) {
            log.error(e.getMessage());
            throw new InternalError("Chat already registered", e);
        }
    }

    @Override
    public void unregister(Long id) {
        chatRepository.removeById(id);
        linkRepository.removeWithZeroSubscriptions();
    }
}
