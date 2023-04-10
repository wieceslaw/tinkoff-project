package ru.tinkoff.edu.java.scrapper.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.entity.SubscriptionEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcSubscriptionRepository {
    private final JdbcTemplate template;
    private final BeanPropertyRowMapper<SubscriptionEntity> mapper = new BeanPropertyRowMapper<>(SubscriptionEntity.class);

    public Integer add(Long chatId, Long linkId) throws DuplicateKeyException {
        return template.update("insert into subscription (chat_id, link_id) values (?, ?)", chatId, linkId);
    }

    public List<SubscriptionEntity> findAll() {
        return template.query("select chat_id, link_id from subscription", mapper);
    }

    public List<SubscriptionEntity> findByChatId(Long chatId) {
        return template.query("select chat_id, link_id from subscription where chat_id = ?", mapper, chatId);
    }

    public Integer remove(Long chatId, Long linkId) {
        return template.update("delete from subscription where chat_id = ? and link_id = ?", chatId, linkId);
    }

    public Integer countSubscriptions(Long linkId) {
        return template.queryForObject("select count(chat_id) from subscription where link_id = ?", Integer.class, linkId);
    }
}
