package com.github.jskov.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = "Invalid command input.";

    @Override
    public String execute(Update update) {
        return UNKNOWN_MESSAGE;
    }
}