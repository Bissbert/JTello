package com.tello.connection.functional.command.model;

import com.tello.connection.CommandStrings;

public class BasicCommand extends AbstractCommand{


    public BasicCommand(String command, boolean isRead) {
        super(command, isRead);
    }

    public BasicCommand(CommandStrings command, boolean isRead) {
        super(command, isRead);
    }

    @Override
    public String compose() {
        return super.getCommand();
    }
}
