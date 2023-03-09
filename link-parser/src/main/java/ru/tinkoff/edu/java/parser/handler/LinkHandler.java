package ru.tinkoff.edu.java.parser.handler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.tinkoff.edu.java.parser.data.LinkData;

public abstract class LinkHandler {
    protected LinkHandler next = null;
    abstract protected LinkData handle(@NotNull String link);

    public @Nullable LinkData handleLink(String link) {
        if (link == null) {
            return null;
        }
        LinkData result = handle(link);
        if (result == null && next != null) {
            return next.handleLink(link);
        }
        return result;
    }

    public LinkHandler setNext(LinkHandler next) {
        this.next = next;
        return next;
    }
}
