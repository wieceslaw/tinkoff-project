package ru.tinkoff.edu.java.scrapper.dto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "link")
@Getter
@Setter
public class LinkEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "linkIdSeq"
    )
    @SequenceGenerator(
            name = "linkIdSeq",
            sequenceName = "link_id_seq"
    )
    private Long id;

    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @Column(name = "last_check_time", nullable = false)
    private OffsetDateTime lastCheckTime;

    @Column(name = "last_update_time")
    private OffsetDateTime lastUpdateTime;

    @ManyToMany
    @JoinTable(
            name = "subscription",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "link_id", referencedColumnName = "id")
    )
    private List<ChatEntity> subscribers;
}
