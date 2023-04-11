package ru.tinkoff.edu.java.scrapper.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowWebClient;
import ru.tinkoff.edu.java.scrapper.dto.client.StackOverflowQuestionResponse;
import ru.tinkoff.edu.java.scrapper.dto.client.StackOverflowQuestionsResponse;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StackOverflowWebService {
    private final StackOverflowWebClient stackOverflowWebClient;

    public StackOverflowQuestionResponse fetchQuestion(Integer id) {
        return stackOverflowWebClient
                .fetchQuestion(id)
                .map(item -> item.items().get(0)).block();
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
