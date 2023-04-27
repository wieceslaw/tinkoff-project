package ru.tinkoff.edu.java.scrapper.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.config.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.service.bot.BotWebService;
import ru.tinkoff.edu.java.scrapper.service.queue.ScrapperQueueProducer;

@Slf4j
@Service
public class UpdatesSendingService {
    private final ScrapperQueueProducer queueProducer;
    private final BotWebService webService;
    private final Boolean useQueue;

    public UpdatesSendingService(
            ScrapperQueueProducer queueProducer,
            BotWebService webService,
            ApplicationConfig config
    ) {
        this.queueProducer = queueProducer;
        this.webService = webService;
        this.useQueue = config.getUseQueue();
    }

    public void sendUpdate(LinkUpdateRequest update) {
        if (useQueue) {
            queueProducer.sendUpdate(update);
        } else {
            webService.sendUpdate(update);
        }
    }
}
