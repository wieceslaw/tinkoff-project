package ru.tinkoff.edu.java.scrapper.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.client.GitHubWebClient;
import ru.tinkoff.edu.java.scrapper.dto.client.GitHubEventResponse;
import ru.tinkoff.edu.java.scrapper.dto.client.UpdatesInfo;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubWebService {
    private final GitHubWebClient gitHubWebClient;

    public UpdatesInfo fetchEventsUpdates(String owner, String repo, OffsetDateTime lastUpdateTimeSaved) {
        List<GitHubEventResponse> events = gitHubWebClient.fetchEvents(owner, repo).block();
        GitHubEventResponse lastEvent = events.get(0);
        List<String> eventsInfo = events
                .stream()
                .filter(event -> event.getCreatedAt().isAfter(lastUpdateTimeSaved))
                // TODO: format created at offsetDateTime
                .map(event -> resolveEventType(event.getType()) + " at " + event.getCreatedAt())
                .toList();
        return new UpdatesInfo(lastEvent.getCreatedAt(), eventsInfo);
    }

    private String resolveEventType(String eventType) {
        return switch (eventType) {
            case "CommitCommentEvent" -> "Commit was committed";
            case "PushEvent" -> "Commit was pushed";
            case "IssueCommentEvent" -> "Issue was commented";
            case "PullRequestReviewCommentEvent" -> "Pull request review was commented";
            default -> "Some update happened";
        };
    }
}
