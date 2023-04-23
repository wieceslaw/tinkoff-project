package ru.tinkoff.edu.java.scrapper.service.domain.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.service.domain.api.SubscriptionService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaSubscriptionServiceTest extends IntegrationEnvironment {
    @Autowired
    private SubscriptionService subscriptionService;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("databaseAccessType", () -> "jpa");
    }

    @Test
    @Transactional
    @Rollback
    void method__action_result() {
        // given

        // when

        // then

    }
}