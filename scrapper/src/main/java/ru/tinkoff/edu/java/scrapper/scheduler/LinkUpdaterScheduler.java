package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.parser.data.GitHubLinkData;
import ru.tinkoff.edu.java.parser.data.LinkData;
import ru.tinkoff.edu.java.parser.data.StackOverflowLinkData;
import ru.tinkoff.edu.java.parser.handler.LinkHandlerChain;
import ru.tinkoff.edu.java.scrapper.config.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.dto.client.UpdatableResponse;
import ru.tinkoff.edu.java.scrapper.dto.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.service.api.SubscriptionService;
import ru.tinkoff.edu.java.scrapper.service.api.UpdateService;
import ru.tinkoff.edu.java.scrapper.service.client.BotWebService;
import ru.tinkoff.edu.java.scrapper.service.client.GitHubWebService;
import ru.tinkoff.edu.java.scrapper.service.client.StackOverflowWebService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {
    private final ApplicationConfig config;
    private final LinkHandlerChain handlerChain;

    private final UpdateService updateService;
    private final SubscriptionService subscriptionService;

    private final GitHubWebService gitHubWebService;
    private final StackOverflowWebService stackOverflowWebService;
    private final BotWebService botWebService;

    @Scheduled(fixedDelayString = "#{@applicationConfig.scheduler.interval}")
    public void update() {
        List<LinkEntity> links = updateService
                .findLinksWithLastCheckedTimeLongAgo(config.getScheduler().getCheckSecondsDelay());
        for (LinkEntity link : links) {
            LinkData linkData = handlerChain.handle(link.getUrl());
            UpdatableResponse response = fetchResponse(linkData);
            updateLink(link, response);
        }
        log.info("Update!");
    }

    private UpdatableResponse fetchResponse(LinkData linkData) {
        return switch (linkData) {
            case GitHubLinkData data ->
                    gitHubWebService.fetchRepository(data.owner(), data.repo());
            case StackOverflowLinkData data ->
                    stackOverflowWebService.fetchQuestion(data.questionId());
        };
    }

    private void updateLink(LinkEntity linkEntity, UpdatableResponse response) {
        if (linkEntity.getLastUpdateTime() == null ||
                linkEntity.getLastUpdateTime().isBefore(response.getLastUpdateTime())) {
            updateService.updateLink(linkEntity, response.getLastUpdateTime());
            List<Long> chatIds = subscriptionService
                    .getLinkSubscribers(linkEntity.getId())
                    .stream()
                    .map(ChatEntity::getId)
                    .toList();
            // TODO: change
            String description = "Update!";
            botWebService.sendUpdate(new LinkUpdateRequest(
                    linkEntity.getId(),
                    linkEntity.getUrl(),
                    description,
                    chatIds
            ));
        }
    }
}
