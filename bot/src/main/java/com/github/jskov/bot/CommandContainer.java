package com.github.jskov.bot;

import com.github.jskov.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommandContainer {

    @Autowired
    private HelpCommand helpCommand;
    @Autowired
    private StartCommand startCommand;
    @Autowired
    private StopCommand stopCommand;
    @Autowired
    private UnknownCommand unknownCommand;


    public Command defineCommand(String commandIdentifier) {
        switch (commandIdentifier) {
            case "/start" : return startCommand;
            case "/help" : return helpCommand;
            case "/stop" : return stopCommand;
            default: return unknownCommand;
        }
    }

}