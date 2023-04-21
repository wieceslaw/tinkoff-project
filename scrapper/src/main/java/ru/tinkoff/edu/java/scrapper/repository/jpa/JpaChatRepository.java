package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.entity.ChatEntity;

@Repository
public interface JpaChatRepository extends JpaRepository<ChatEntity, Long> {
}
