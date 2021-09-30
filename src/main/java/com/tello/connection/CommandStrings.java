package com.tello.connection;

import java.io.Serializable;
import java.util.stream.IntStream;

public enum CommandStrings implements CharSequence, Serializable {

    COMMAND("command");

    String command;

    private CommandStrings(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public int length() {
        return this.command.length();
    }

    @Override
    public char charAt(int index) {
        return this.command.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.command.subSequence(start, end);
    }

    @Override
    public IntStream chars() {
        return this.command.chars();
    }

    @Override
    public IntStream codePoints() {
        return this.command.codePoints();
    }
}
