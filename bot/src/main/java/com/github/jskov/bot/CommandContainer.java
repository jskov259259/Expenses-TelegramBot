package com.github.jskov.bot;

import com.github.jskov.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.github.jskov.bot.CommandName.*;


@Component
public class CommandContainer {

    private HelpCommand helpCommand;
    private StartCommand startCommand;
    private StopCommand stopCommand;
    private UnknownCommand unknownCommand;

    private Map<String, Command> commandMap = new HashMap<>();

    public Command defineCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
}

    @Autowired
    public void setHelpCommand(HelpCommand helpCommand) {
        this.helpCommand = helpCommand;
        commandMap.put(HELP.getCommandName(), helpCommand);
    }
    @Autowired
    public void setStartCommand(StartCommand startCommand) {
        this.startCommand = startCommand;
        commandMap.put(START.getCommandName(), startCommand);
    }
    @Autowired
    public void setStopCommand(StopCommand stopCommand) {
        this.stopCommand = stopCommand;
        commandMap.put(STOP.getCommandName(), stopCommand);
    }
    @Autowired
    public void setUnknownCommand(UnknownCommand unknownCommand) {
        this.unknownCommand = unknownCommand;
    }
}