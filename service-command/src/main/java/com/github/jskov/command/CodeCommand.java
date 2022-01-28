package com.github.jskov.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class CodeCommand implements Command {

    public static final String CODE_MESSAGE = "Code list message";


    @Override
    public String execute(Update update) {
        return CODE_MESSAGE;
    }
}
