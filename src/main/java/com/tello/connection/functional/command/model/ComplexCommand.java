package com.tello.connection.functional.command.model;

import com.tello.connection.CommandStrings;
import com.tello.logger.Logger;

import java.util.LinkedList;
import java.util.StringJoiner;

public class ComplexCommand extends AbstractCommand {

    LinkedList<Object> parameters;

    public ComplexCommand(String command, boolean isRead) {
        super(command, isRead);
    }

    public ComplexCommand(CommandStrings command, boolean isRead) {
        super(command, isRead);
    }

    public ComplexCommand(BasicCommand basicCommand){
        super(basicCommand.getCommand(), basicCommand.isRead());
    }

    public boolean addParam(Object param) {
        if (param != null) {
            return parameters.add(param);
        }
        Logger.INSTANCE.warning("The param you tried to add is null");
        return false;
    }

    @Override
    public String compose() {
        if (parameters.size() > 0) {
            StringJoiner finalCommand = new StringJoiner(" ");
            finalCommand.add(getCommand());
            for (Object param : parameters) {
                finalCommand.add(param.toString());
            }
            return finalCommand.toString();
        }
        return getCommand();
    }
}
