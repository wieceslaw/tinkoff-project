package ru.tinkoff.edu.java.scrapper.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcLinkRepository {
    private final JdbcTemplate template;
    private final BeanPropertyRowMapper<LinkEntity> mapper = new BeanPropertyRowMapper<>(LinkEntity.class);

    public Long add(String url) throws DuplicateKeyException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update("insert into link (url) values (?)", url, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public LinkEntity find(String url) throws EmptyResultDataAccessException {
        return template.queryForObject("select id, url from link where url = ?", mapper, url);
    }

    public LinkEntity findById(Long id) throws EmptyResultDataAccessException {
        return template.queryForObject("select id, url from link where id = ?", mapper, id);
    }

    public List<LinkEntity> findWithChatSubscription(Long chatId) {
        return template.query("select id, url from link where id in (select link_id from subscription where chat_id = ?)", mapper, chatId);
    }

    public List<LinkEntity> findAll() {
        return template.query("select id, url from link", mapper);
    }

    public Integer remove(String url) {
        return template.update("delete from link where url = ?", url);
    }

    public Integer removeLinkById(Long id) {
        return template.update("delete from link where id = ?", id);
    }

    public Integer removeWithZeroSubscriptions() {
        return template.update("DELETE FROM link WHERE (SELECT COUNT(link_id) FROM subscription WHERE link_id = link.id) = 0");
    }
}