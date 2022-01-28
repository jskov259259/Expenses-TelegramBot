package com.github.jskov.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class StopCommand implements Command {

    public static final String STOP_MESSAGE = "Work completed. Bye";

    @Override
    public String execute(Update update) {
        return STOP_MESSAGE;
    }
}