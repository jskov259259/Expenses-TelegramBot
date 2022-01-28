//package com.github.jskov.bot;
//
//import com.github.jskov.service.SendBotMessageService;
//import com.google.common.collect.ImmutableMap;
//
//import static com.github.jskov.bot.CommandName.*;
//
//public class CommandContainer {
//
//    private final ImmutableMap<String, Command> commandMap;
//    private final Command unknownCommand;
//
//    public CommandContainer(SendBotMessageService sendBotMessageService) {
//        commandMap = ImmutableMap.<String, Command>builder()
//                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
//                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
//                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
//                .build();
//        unknownCommand = new UnknownCommand(sendBotMessageService);
//    }
//
//    public Command retrieveCommand(String commandIdentifier) {
//        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
//    }
//
//}