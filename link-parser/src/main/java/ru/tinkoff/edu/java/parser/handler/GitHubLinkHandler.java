package ru.tinkoff.edu.java.parser.handler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.parser.data.GitHubLinkData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GitHubLinkHandler extends LinkHandler {
    private final Pattern pattern = Pattern.compile(
            "^https://github\\.com/([^/]+)/([^/]+)/?$"
    );

    @Override
    public @Nullable GitHubLinkData handle(@NotNull String link) {
        Matcher matcher = pattern.matcher(link);
        if (!matcher.matches()) return null;
        String username = matcher.group(1);
        String repository = matcher.group(2);
        return new GitHubLinkData(username, repository);
    }
}
