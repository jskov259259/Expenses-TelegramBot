package com.github.jskov.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class AddCommand implements Command {

    public static final String ADD_MESSAGE = "Information added";


    @Override
    public String execute(Update update) {
        return ADD_MESSAGE;
    }
}