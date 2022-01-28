package com.github.jskov.bot;

import com.github.jskov.command.Command;
import com.github.jskov.command.HelpCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ExpensesTelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;

    private CommandContainer commandContainer;

    @Autowired
    ExpensesTelegramBot(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            //define incoming command
            String message = update.getMessage().getText().trim();
            String commandIdentifier = message.split(" ")[0].toLowerCase();

            //define command service and response message
            Command command = commandContainer.defineCommand(commandIdentifier);
            String responseMessage = command.execute(update);

            //send response message
            sendMessage(update, responseMessage);
        }
        }

    private void sendMessage(Update update, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }


}
