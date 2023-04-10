package ru.tinkoff.edu.java.scrapper.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcSubscriptionRepositoryTest {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private JdbcSubscriptionRepository subscriptionRepository;

    @Test
    @Transactional
    @Rollback
    void add__addOne_oneAdded() {
        // given

        // when

        // then
    }

    @Test
    @Transactional
    @Rollback
    void add__alreadyExist_throwsException() {
        // given

        // when

        // then
    }

    @Test
    @Transactional
    @Rollback
    void findAll__nothingExists_zeroItemsReturned() {
        // given

        // when

        // then
    }

    @Test
    @Transactional
    @Rollback
    void findAll__oneExists_oneReturned() {
        // given

        // when

        // then
    }

    @Test
    @Transactional
    @Rollback
    void remove__oneExists_oneRemoved() {
        // given

        // when

        // then
    }

    @Test
    @Transactional
    @Rollback
    void remove__notExists_zeroRemoved() {
        // given

        // when

        // then
    }

    @Test
    @Transactional
    @Rollback
    void countSubscriptions__oneSubscriber_oneCounted() {
        // given

        // when

        // then
    }

    @Test
    @Transactional
    @Rollback
    void countSubscriptions__zeroSubscribers_zeroCounted() {
        // given

        // when

        // then
    }
}