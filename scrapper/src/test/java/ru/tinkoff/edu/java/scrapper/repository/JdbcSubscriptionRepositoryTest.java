package ru.tinkoff.edu.java.scrapper.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcSubscriptionRepositoryTest {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private JdbcSubscriptionRepository jdbcSubscriptionRepository;


}