package ru.tinkoff.edu.java.parser.handler;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.parser.data.GitHubLinkData;
import ru.tinkoff.edu.java.parser.data.LinkData;
import ru.tinkoff.edu.java.parser.data.StackOverflowLinkData;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class MainLinkHandler implements LinkHandler {
    private List<LinkHandler> handlers;

    @Override
    public @Nullable LinkData handleLink(@NotNull String link) {
        return handlers.stream()
                .map(handler -> handler.handleLink(link))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        MainLinkHandler mainLinkHandler = new MainLinkHandler(Arrays.asList(
                new GitHubLinkHandler(),
                new StackOverflowLinkHandler()
        ));
        String link = "https://github.com/Wieceslaw/tinkoff-project/";
        LinkData linkData = mainLinkHandler.handleLink(link);
        if (linkData instanceof GitHubLinkData gitHubLinkData) {
            System.out.println(gitHubLinkData.repository() + ' ' + gitHubLinkData.username());
        } else if (linkData instanceof StackOverflowLinkData stackOverflowLinkData) {
            System.out.println(stackOverflowLinkData.questionId());
        } else {
            System.out.println("No match");
        }
    }
}
