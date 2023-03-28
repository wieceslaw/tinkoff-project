package ru.tinkoff.edu.java.parser.handler;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tinkoff.edu.java.parser.data.LinkData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkHandlerChainTest {
    private final LinkHandlerChain linkHandlerChain;

    LinkHandlerChainTest() {
        List<LinkHandler> handlers = Arrays.asList(
                new GitHubLinkHandler(),
                new StackOverflowLinkHandler()
        );
        linkHandlerChain = new LinkHandlerChain(handlers);
    }

    @ParameterizedTest
    @MethodSource({
            "getParametersWrongFormat",
            "getParametersWrongDomain",
            "getParametersCorrect"
    })
    void handle__incorrectLink_returnNull(String link, boolean correct) {
        // given

        // when
        LinkData data = linkHandlerChain.handle(link);

        // then
        assertEquals(data != null, correct);
    }

    private Stream<Arguments> getParametersCorrect() {
        return Stream.of(
                Arguments.of("https://github.com/Wieceslaw/tinkoff-project", true),
                Arguments.of("https://stackoverflow.com/questions/9706688/what", true),
                Arguments.of("https://stackoverflow.com/questions/9706688/123123", true),
                Arguments.of("https://github.com/Wieceslaw/214123", true)
        );
    }

    private Stream<Arguments> getParametersWrongFormat() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("1", false),
                Arguments.of("github.com/", false),
                Arguments.of("https://github.com/", false),
                Arguments.of("https://github.com/Wieceslaw", false),
                Arguments.of("https://github.com/Wieceslaw/asd/asd", false),
                Arguments.of("https://stackoverflow.random/questions/sadasd/what", false),
                Arguments.of("https://stackoverflow.random/questions/9706688/what/asdasd", false),
                Arguments.of("https://stackoverflow.random/asdsa/9706688/what", false)
        );
    }

    private Stream<Arguments> getParametersWrongDomain() {
        return Stream.of(
                Arguments.of("https://ru.wikipedia.org/questions/9706688/what", false),
                Arguments.of("https://stackoverflow.random/questions/9706688/what", false),
                Arguments.of("https://github.random/Wieceslaw/tinkoff-project", false)
        );
    }
}