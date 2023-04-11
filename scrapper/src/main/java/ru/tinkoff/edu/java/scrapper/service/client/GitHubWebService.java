package ru.tinkoff.edu.java.scrapper.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.client.GitHubWebClient;
import ru.tinkoff.edu.java.scrapper.dto.client.GitHubRepositoryResponse;

@Service
@RequiredArgsConstructor
public class GitHubWebService {
    private final GitHubWebClient gitHubWebClient;

    public GitHubRepositoryResponse fetchRepository(String owner, String repo) {
        return gitHubWebClient.fetchRepo(owner, repo).block();
    }
}
