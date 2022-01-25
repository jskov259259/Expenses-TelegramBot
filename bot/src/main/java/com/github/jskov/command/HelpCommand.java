package com.github.jskov.command;

import com.github.jskov.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.jskov.command.CommandName.*;

public class HelpCommand implements Command {

        private final SendBotMessageService sendBotMessageService;

        public static final String HELP_MESSAGE = String.format("✨<b>Available commands</b>✨\n\n"

                        + "%s - Start the bot\n"
                        + "%s - Stop the bot\n\n"
                        + "%s - Get help with a bot\n",
                START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

        public HelpCommand(SendBotMessageService sendBotMessageService) {
            this.sendBotMessageService = sendBotMessageService;
        }

        @Override
        public void execute(Update update) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
        }
    }
