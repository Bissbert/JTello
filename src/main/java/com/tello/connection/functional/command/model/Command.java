package com.tello.connection.functional.command.model;

public interface Command {
    /**
     * creates and returns the command in its full form
     * @return the command in its full form
     */
    String compose();
}
