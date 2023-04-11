package ru.tinkoff.edu.java.scrapper.scheduler;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.config.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.dto.entity.LinkEntity;
import ru.tinkoff.edu.java.scrapper.service.api.UpdateService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkUpdaterScheduler {
    private final ApplicationConfig config;
    private final UpdateService updateService;

    @Scheduled(fixedDelayString = "#{@applicationConfig.scheduler.interval}")
    public void update() {
        List<LinkEntity> links = updateService.findLinksWithLastCheckedTimeLongAgo(config.getScheduler().getCheckSecondsDelay());
        System.out.println(links);
        // for each link:
        //     new_update_time = client(link.url).updatedAt
        //     if link.update_time == null || link.update_time < new_update_time:
        //         service.update_link(link, new_update_time)
        log.info("Update!");
    }
}
