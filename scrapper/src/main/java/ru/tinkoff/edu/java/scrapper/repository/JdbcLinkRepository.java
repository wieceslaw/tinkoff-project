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

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcLinkRepository {
    private final JdbcTemplate template;
    private final BeanPropertyRowMapper<LinkEntity> mapper = new BeanPropertyRowMapper<>(LinkEntity.class);

    public Long add(String url) throws DuplicateKeyException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement ps = con.prepareStatement("""
                    insert into link (url) 
                    values (?)
                    """, new String[]{"id"});
            ps.setString(1, url);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public LinkEntity find(String url) throws EmptyResultDataAccessException {
        return template.queryForObject("""
                select id, url, last_check_time, last_update_time 
                from link 
                where url = ?
                """, mapper, url);
    }

    public LinkEntity findById(Long id) throws EmptyResultDataAccessException {
        return template.queryForObject("""
                select id, url, last_check_time, last_update_time 
                from link 
                where id = ?
                """, mapper, id);
    }

    public List<LinkEntity> findAll() {
        return template.query("""
                select id, url, last_check_time, last_update_time 
                from link
                """, mapper);
    }

    public List<LinkEntity> findWithChatSubscription(Long chatId) {
        return template.query("""
                select id, url, last_check_time, last_update_time
                from link 
                where id in (select link_id from subscription where chat_id = ?)
                """, mapper, chatId);
    }

    public List<LinkEntity> findWithLastCheckedTimeLongAgo(Integer secondsDelta) {
        return template.query("""
                update link
                set last_check_time = now()
                where now() - last_check_time > ?::interval
                returning id, url, last_check_time, last_update_time
                """, mapper, secondsDelta.toString() + " seconds");
    }

    public Integer remove(String url) {
        return template.update("""
                delete from link 
                where url = ?
                """, url);
    }

    public Integer removeById(Long id) {
        return template.update("""
                delete from link 
                where id = ?
                """, id);
    }

    public Integer removeWithZeroSubscriptions() {
        return template.update("""
                delete from link 
                where (select count(link_id) from subscription where link_id = link.id) = 0
                """);
    }
}
