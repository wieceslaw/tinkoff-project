package ru.tinkoff.edu.java.scrapper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.client.stackoverflow.StackOverflowWebClient;
import ru.tinkoff.edu.java.scrapper.client.stackoverflow.dto.StackOverflowQuestionResponse;

@RequiredArgsConstructor
@Service
public class StackOverflowService {
    private final StackOverflowWebClient stackOverflowWebClient;

    public Mono<StackOverflowQuestionResponse> fetchQuestion(Integer id) {
        // TODO: implement fetching single/multiple questions
        return stackOverflowWebClient.fetchQuestion(id)
                .map(item -> item.items().get(0));
    }
}
