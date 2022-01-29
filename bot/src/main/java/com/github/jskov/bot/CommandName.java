package com.github.jskov.bot;

public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    ADD("/add"),
    CODE("/code"),
    GET("/get"),
    ADDCATEGORY("/addcategory");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}