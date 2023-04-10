package ru.tinkoff.edu.java.scrapper.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;
import ru.tinkoff.edu.java.scrapper.dto.entity.ChatEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JdbcChatRepositoryTest extends IntegrationEnvironment {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private JdbcChatRepository chatRepository;

    @Test
    @Transactional
    @Rollback
    void add__addOne_oneAdded() {
        // given
        Long id = 1L;

        // when
        int added = chatRepository.add(id);
        List<ChatEntity> all = findAll();

        // then
        assertEquals(added, 1);
        assertEquals(all.size(), 1);
        assertEquals(all.get(0), new ChatEntity(id));
    }

    @Test
    @Transactional
    @Rollback
    void add__duplicate_throwsException() {
        // given
        Long id = 1L;

        // when
        int added = chatRepository.add(id);

        // then
        assertEquals(added, 1);
        assertThrows(DuplicateKeyException.class, () -> chatRepository.add(id));
    }

    @Test
    @Transactional
    @Rollback
    void remove__oneExists_oneRemoved() {
        // given
        Long id = 1L;
        create(id);

        // when
        int removed = chatRepository.removeById(id);
        List<ChatEntity> all = findAll();

        // then
        assertEquals(all.size(), 0);
        assertEquals(removed, 1);
    }

    @Test
    @Transactional
    @Rollback
    void remove__notExists_zeroRemoved() {
        // given
        Long id = 2L;

        // when
        int removed = chatRepository.removeById(id);

        // then
        assertEquals(removed, 0);
    }

    @Test
    @Transactional
    @Rollback
    void getAll__nothingExists_returnsZeroItems() {
        // given

        // when
        List<ChatEntity> all = chatRepository.findAll();

        // then
        assertEquals(all.size(), 0);
    }

    @Test
    @Transactional
    @Rollback
    void getAll__oneExists_returnsOne() {
        // given
        Long id = 1L;
        create(id);

        // when
        List<ChatEntity> all = chatRepository.findAll();

        // then
        assertEquals(all.size(), 0);
    }

    private List<ChatEntity> findAll() {
        return template.query("select id from chat", new BeanPropertyRowMapper<>(ChatEntity.class));
    }

    private void create(Long id) {
        template.update("insert into chat (id) values (?)", id);
    }
}