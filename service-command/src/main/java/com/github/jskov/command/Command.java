package com.github.jskov.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * command.Command interface for handling telegram-bot commands.
 */
public interface Command {

        /**
         * Main method, which is executing com.github.jskov.command logic.
         *
         * @param update provided {@link Update} object with all the needed data for com.github.jskov.command.
         */
    String execute(Update update);

}
