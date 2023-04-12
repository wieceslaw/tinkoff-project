package ru.tinkoff.edu.java.scrapper.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.entity.ChatEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcChatRepository {
    private final JdbcTemplate template;
    private final BeanPropertyRowMapper<ChatEntity> mapper = new BeanPropertyRowMapper<>(ChatEntity.class);

    public Integer add(Long id) throws DuplicateKeyException {
        return template.update("""
                insert into chat (id) values (?)
                """, id);
    }

    public Integer removeById(Long id) {
        return template.update("""
                delete from chat where id = ?
                """, id);
    }

    public List<ChatEntity> findAll() {
        return template.query("""
                select id from chat
                """, mapper);
    }

    public List<ChatEntity> findAllSubscribers(Long linkId) {
        return template.query("""
                select id 
                from chat
                join subscription on chat.id = chat_id
                where link_id = ?
                """, mapper, linkId);
    }
}
