package com.github.jskov.command;

import com.github.jskov.command.Command;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class HelpCommand implements Command {

        public static final String HELP_MESSAGE = "Help message";


        @Override
        public String execute(Update update) {
            return HELP_MESSAGE;
        }
    }
