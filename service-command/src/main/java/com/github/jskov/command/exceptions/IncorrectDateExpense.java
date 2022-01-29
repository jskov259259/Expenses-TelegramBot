package com.github.jskov.command.exceptions;

public class IncorrectDateExpense extends Exception {

    private String incomingMessage;

    public IncorrectDateExpense(String message, String incomingMessage) {
        super(message);
        this.incomingMessage = incomingMessage;
    }

    public String getIncomingMessage() {
        return incomingMessage;
    }
}
