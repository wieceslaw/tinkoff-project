package ru.tinkoff.edu.java.scrapper.service.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.config.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdateRequest;

@Slf4j
@Service
public class ScrapperQueueProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public ScrapperQueueProducer(RabbitTemplate rabbitTemplate, ApplicationConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = config.getRabbitQueue().getExchangeName();
        this.routingKey = config.getRabbitQueue().getRoutingKey();
    }

    public void sendUpdate(LinkUpdateRequest update) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, update);
    }
}