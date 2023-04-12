package ru.tinkoff.edu.java.scrapper.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowWebClient;
import ru.tinkoff.edu.java.scrapper.dto.client.StackOverflowQuestionResponse;
import ru.tinkoff.edu.java.scrapper.dto.client.StackOverflowQuestionsResponse;
import ru.tinkoff.edu.java.scrapper.dto.client.UpdatesInfo;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StackOverflowWebService {
    private final StackOverflowWebClient stackOverflowWebClient;

    public UpdatesInfo fetchQuestionUpdates(Integer id) {
        StackOverflowQuestionResponse response = stackOverflowWebClient
                .fetchQuestion(id)
                .map(item -> item.items().get(0)).block();
        return new UpdatesInfo(
                response.lastActivityDate(),
                List.of("Check out new update from " + response.title())
        );
    }

    public List<StackOverflowQuestionResponse> fetchQuestions(List<Integer> ids) {
        return stackOverflowWebClient
                .fetchQuestions(ids
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(";")))
                .map(StackOverflowQuestionsResponse::items).block();
    }
}
