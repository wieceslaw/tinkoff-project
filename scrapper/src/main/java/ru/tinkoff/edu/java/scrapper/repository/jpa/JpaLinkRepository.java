package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;

@Repository
public interface JpaLinkRepository extends JpaRepository<LinkEntity, Long> {
    @Query("""
            delete from LinkEntity l
            where l.id in 
                (select l.id from l 
                left outer join SubscriptionEntity s on l.id = s.linkId 
                where s.linkId is NULL)
            """)
    void deleteWithZeroSubscribers();
}
