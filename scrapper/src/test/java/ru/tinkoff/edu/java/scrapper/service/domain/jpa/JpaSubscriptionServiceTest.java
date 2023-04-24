package ru.tinkoff.edu.java.scrapper.service.domain.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.service.domain.api.SubscriptionService;

@SpringBootTest
class JpaSubscriptionServiceTest extends IntegrationEnvironment {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ServicesTestHelper helper;

    @Test
    @Transactional
    @Rollback
    void subscribe__linkExists_createsSubscription() {
        // given
        // add chat
        // add link

        // when
        // service.subscribe

        // then
        // assert subscription exists
    }

    @Test
    @Transactional
    @Rollback
    void subscribe__linkDoesNotExist_createsLinkAndSubscription() {
        // given
        // addChat
        // assert link does not exist

        // when
        // service.subscribe

        // then
        // assert link exists
        // assert subscription exists
    }

    @Test
    @Transactional
    @Rollback
    void subscribe__chatDoesNotExist_throwsException() {
        // given
        // assert chat does not exist

        // when

        // then
        // assert service.subscribe throws exception
    }

    @Test
    @Transactional
    @Rollback
    void subscribe__alreadySubscribed_throwsException() {
        // given
        // addChat
        // service.subscribe(chat, url)

        // when

        // then
        // assert service.subscribe throws exception
    }

    @Test
    @Transactional
    @Rollback
    void unsubscribe__chatDoesNotExist_throwsException() {
        // given
        // assert chat does not exist
        // addLink(id, url)

        // when

        // then
        // assert service.unsubscribe throws IllegalArgumentException("Chat does not exist")
    }

    @Test
    @Transactional
    @Rollback
    void unsubscribe__linkDoesNotExist_throwsException() {
        // given
        // addChat(id)
        // assert link does not exist

        // when

        // then
        // assert service.unsubscribe() throws IllegalArgumentException("Link does not exist")
    }

    @Test
    @Transactional
    @Rollback
    void unsubscribe__linkHasOnlySubscriber_removesLinkAndSubscription() {
        // given
        // addChat(id)
        // addLink(id, link)
        // addSubscription(chatId, linkId)

        // when
        // assert link does exist
        // service.unsubscribe()

        // then
        // assert link does not exist
        // assert subscription does not exist
    }

    @Test
    @Transactional
    @Rollback
    void unsubscribe__linkHasTwoSubscribers_removesOnlySubscription() {
        // given
        // addChat(chatId1)
        // addChat(chatId2)
        // addLink(id, link)
        // addSubscription(chatId1, linkId)
        // addSubscription(chatId2, linkId)


        // when
        // assert link does exist
        // service.unsubscribe()

        // then
        // assert link does exist
        // assert subscription does not exist
    }

    @Test
    @Transactional
    @Rollback
    void getChatSubscriptions__hasZeroSubscriptions_emptyList() {
        // given
        // addChat(id)

        // when
        // list = service.getChatSubscriptions()

        // then
        // assert list.empty()
    }

    @Test
    @Transactional
    @Rollback
    void getChatSubscriptions__hasTwoSubscriptions_twoCorrectItems() {
        // given
        // addChat(chatId)
        // addLink(linkId1, url)
        // addLink(linkId2, url)
        // addSubscription(chatId, linkId1)
        // addSubscription(chatId, linkId2)

        // when
        // list = service.getChatSubscriptions()

        // then
        // assert list.size == 2
        // assert list.get(0).getId() = linkId1
        // assert link.get(1).getId() = linkId2
    }

    @Test
    @Transactional
    @Rollback
    void getLinkSubscribers__hasOneSubscriber_oneCorrectItem() {
        // given
        // addChat(chatId)
        // addLink(linkId, url)
        // addSubscription(chatId, linkId)

        // when
        // list = service.getLinkSubscribers()

        // then
        // assert list.size == 1
        // assert list.get(0).getId() = chatId
    }
}