package com.github.jskov.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class GetCommand implements Command {

    public static final String GET_MESSAGE = "Get message";


    @Override
    public String execute(Update update) {
        return GET_MESSAGE;
    }
}