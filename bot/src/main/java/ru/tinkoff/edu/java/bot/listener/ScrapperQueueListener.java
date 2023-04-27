package ru.tinkoff.edu.java.bot.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.controller.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.TrackerBot;

@Slf4j
@Component
@RabbitListener(queues = "${app.rabbit-queue.queue-name}")
@RequiredArgsConstructor
public class ScrapperQueueListener {
    private final TrackerBot bot;

    @RabbitHandler
    public void receiver(LinkUpdateRequest message) {
        log.info(message.toString());
        bot.sendUpdates(message);
    }
}
