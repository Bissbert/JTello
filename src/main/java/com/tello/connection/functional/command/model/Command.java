package com.tello.connection.functional.command.model;

public interface Command {
    /**
     * creates and returns the command in its full form
     * @return the command in its full form
     */
    String compose();

    /**
     * sets if its read
     * @param read
     */
    void setRead(boolean read);

    /**
     * if the command is reading something
     * @return where the command is read
     */
    boolean isRead();
}
