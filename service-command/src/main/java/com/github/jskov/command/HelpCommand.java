package com.github.jskov.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class HelpCommand implements Command {

        public static final String HELP_MESSAGE = String.format("I can help you add expenses and get statistics\n" +
                "<b>Use following commands:</b>\n" +
                "/code - get category code\n" +
                "/add - [code] [price] - add expense information. Use delimiter '.'\n\n" +
                "<b>Use following command to get statistics</b>\n" +
                "/get dd.mm.yyyy - get information for the chosen day\n" +
                "/get dd.mm.yyyy-dd.mm.yyyy - get information for the chosen period\n" +
                "/get [Period world] - get information for the specific period\n" +
                "Period worlds: DAY, WEEK, MONTH, YEAR, ALL");


        @Override
        public String execute(Update update) {
            return HELP_MESSAGE;
        }
    }
