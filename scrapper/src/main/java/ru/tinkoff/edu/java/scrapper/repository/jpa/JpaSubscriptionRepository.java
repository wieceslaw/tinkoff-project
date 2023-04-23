package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.dto.entity.SubscriptionEntity;
import ru.tinkoff.edu.java.scrapper.dto.entity.SubscriptionPk;

@Repository
public interface JpaSubscriptionRepository extends JpaRepository<SubscriptionEntity, SubscriptionPk> {
    Integer countByLinkId(Long linkId);
}
