package ru.tinkoff.edu.java.scrapper.service.domain.jooq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
=======
import ru.tinkoff.edu.java.scrapper.exception.InternalError;
>>>>>>> 8140abd (resolved)
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.repository.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.domain.api.ChatService;

@Slf4j
@Service
@RequiredArgsConstructor
<<<<<<< HEAD
=======
@Transactional(readOnly = true)
>>>>>>> 8140abd (resolved)
public class JooqChatService implements ChatService {
    private final JooqLinkRepository linkRepository;
    private final JooqChatRepository chatRepository;

    @Override
    @Transactional
    public void register(Long id) {
        if (chatRepository.findById(id) == null) {
            chatRepository.add(id);
        } else {
            log.error("Chat duplication error");
<<<<<<< HEAD
            throw new IllegalArgumentException("Chat already registered");
=======
            throw new InternalError("Chat already registered");
>>>>>>> 8140abd (resolved)
        }
    }

    @Override
    @Transactional
    public void unregister(Long id) {
        chatRepository.removeById(id);
        linkRepository.removeWithZeroSubscriptions();
    }
}
