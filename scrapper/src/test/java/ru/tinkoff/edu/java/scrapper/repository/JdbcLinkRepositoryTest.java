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
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JdbcLinkRepositoryTest extends IntegrationEnvironment {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private JdbcLinkRepository linkRepository;

    @Test
    @Transactional
    @Rollback
    void add__addOne_oneAdded() {
        // given
        String url = "https://github.com/Wieceslaw/tinkoff-project";

        // when
        int countBefore = findAll().size();
        int added = linkRepository.add(url);
        int countAfter = findAll().size();

        // then
        assertEquals(added, 1);
        assertEquals(countBefore + 1, countAfter);
    }

    @Test
    @Transactional
    @Rollback
    void add__duplicate_throwsException() {
        // given
        String url = "https://github.com/Wieceslaw/tinkoff-project";

        // when
        int added = linkRepository.add(url);

        // then
        assertEquals(added, 1);
        assertThrows(DuplicateKeyException.class, () -> linkRepository.add(url));
    }

    @Test
    @Transactional
    @Rollback
    void remove__oneExist_oneRemoved() {
        // given
        String url = "https://github.com/Wieceslaw/tinkoff-project";
        create(url);

        // when
        int countBefore = findAll().size();
        int removed = linkRepository.remove(url);
        int countAfter = findAll().size();

        // then
        assertEquals(removed, 1);
        assertEquals(countBefore - 1, countAfter);
    }

    @Test
    @Transactional
    @Rollback
    void remove__notExist_zeroRemoved() {
        // given
        String url = "https://github.com/Wieceslaw/tinkoff-project";

        // when
        int countBefore = findAll().size();
        int removed = linkRepository.remove(url);
        int countAfter = findAll().size();

        // then
        assertEquals(removed, 0);
        assertEquals(countBefore, countAfter);
    }

    @Test
    @Transactional
    @Rollback
    void getAll__nothingExists_returnsZeroItems() {
        // given

        // when
        List<LinkEntity> all = linkRepository.findAll();

        // then
        assertEquals(all.size(), 0);
    }

    @Test
    @Transactional
    @Rollback
    void getAll__oneExists_returnsOne() {
        // given
        String url = "https://github.com/Wieceslaw/tinkoff-project";
        create(url);

        // when
        List<LinkEntity> all = linkRepository.findAll();

        // then
        assertEquals(all.size(), 1);
    }

    private List<LinkEntity> findAll() {
        return template.query("select id, url from link", new BeanPropertyRowMapper<>(LinkEntity.class));
    }

    private void create(String url) {
        template.update("insert into link (url) values (?)", url);
    }
}