package ru.tinkoff.edu.java.bot.telegram.command;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public interface PublicCommand extends Command {
    String command();

    String description();

    default BotCommand toBotCommand() {
        return new BotCommand(command(), description());
    }
}
