package ru.tinkoff.edu.java.scrapper.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.parser.data.GitHubLinkData;
import ru.tinkoff.edu.java.parser.data.LinkData;
import ru.tinkoff.edu.java.parser.data.StackOverflowLinkData;
import ru.tinkoff.edu.java.parser.handler.LinkHandlerChain;
import ru.tinkoff.edu.java.scrapper.config.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.dto.bot.LinkUpdateRequest;
import ru.tinkoff.edu.java.scrapper.dto.client.UpdatesInfo;
import ru.tinkoff.edu.java.scrapper.dto.entity.ChatEntity;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.service.api.SubscriptionService;
import ru.tinkoff.edu.java.scrapper.service.api.UpdateService;
import ru.tinkoff.edu.java.scrapper.service.client.BotWebService;
import ru.tinkoff.edu.java.scrapper.service.client.GitHubWebService;
import ru.tinkoff.edu.java.scrapper.service.client.StackOverflowWebService;

import java.time.OffsetDateTime;
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
            UpdatesInfo updatesInfo = fetchUpdates(linkData, link.getLastUpdateTime());
            updateLink(link, updatesInfo);
        }
        log.info("Update!");
    }

    private UpdatesInfo fetchUpdates(LinkData linkData, OffsetDateTime lastUpdateTimeSaved) {
        return switch (linkData) {
            case GitHubLinkData data -> gitHubWebService.fetchEventsUpdates(data.owner(), data.repo(), lastUpdateTimeSaved);
            case StackOverflowLinkData data -> stackOverflowWebService.fetchQuestionUpdates(data.questionId());
        };
    }

    private void updateLink(LinkEntity linkEntity, UpdatesInfo updatesInfo) {
        if (linkEntity.getLastUpdateTime() == null ||
                linkEntity.getLastUpdateTime().isBefore(updatesInfo.lastUpdateTime())) {
            updateService.updateLink(linkEntity, updatesInfo.lastUpdateTime());
            botWebService.sendUpdate(new LinkUpdateRequest(
                    linkEntity.getId(),
                    linkEntity.getUrl(),
                    Strings.join(updatesInfo.updates(), '\n'),
                    getChatsIds(linkEntity.getId())
            ));
        }
    }

    private List<Long> getChatsIds(Long linkId) {
        return subscriptionService
                .getLinkSubscribers(linkId)
                .stream()
                .map(ChatEntity::getId)
                .toList();
    }
}
