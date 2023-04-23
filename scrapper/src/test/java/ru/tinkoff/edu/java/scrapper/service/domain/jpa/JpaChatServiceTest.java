package ru.tinkoff.edu.java.scrapper.service.domain.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.service.domain.api.ChatService;

@SpringBootTest
class JpaChatServiceTest extends IntegrationEnvironment {
    @Autowired
    private ChatService chatService;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("databaseAccessType", () -> "jpa");
    }

    @Test
    @Transactional
    @Rollback
    void register__chatDoesNotExist_chatRegistered() {
        // given
        Long chatId = 1L;

        // when
        chatService.register(chatId);

        // then

    }

    @Test
    @Transactional
    @Rollback
    void register__chatDoesExist_throwsException() {
        // given

        // when

        // then

    }

    @Test
    @Transactional
    @Rollback
    void unregister__chatDoesExist_chatUnregistered() {
        // given

        // when

        // then

    }

    @Test
    @Transactional
    @Rollback
    void unregister__chatDoesNotExist_nothingHappened() {
        // given

        // when

        // then

    }
}