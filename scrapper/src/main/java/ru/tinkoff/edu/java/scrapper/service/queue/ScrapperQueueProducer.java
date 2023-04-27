package ru.tinkoff.edu.java.scrapper.service.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdateRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapperQueueProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendUpdate(LinkUpdateRequest update) {
        rabbitTemplate.convertAndSend("updates", "upd", update);
    }
}