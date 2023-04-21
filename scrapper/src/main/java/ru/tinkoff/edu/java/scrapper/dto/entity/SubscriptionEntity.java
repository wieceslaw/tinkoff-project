package ru.tinkoff.edu.java.scrapper.dto.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscription")
@IdClass(SubscriptionPk.class)
public class SubscriptionEntity {
    @Id
    private Long chatId;
    @Id
    private Long linkId;
}
