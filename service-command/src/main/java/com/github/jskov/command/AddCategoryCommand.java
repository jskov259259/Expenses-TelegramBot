package com.github.jskov.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class AddCategoryCommand implements Command {

    public static final String ADD_CATEGORY_MESSAGE = "Add Category message";

    @Override
    public String execute(Update update) {
        return ADD_CATEGORY_MESSAGE;
    }
}
