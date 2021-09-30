package com.tello.connection.functional.command.model;

import com.tello.connection.CommandStrings;

import java.util.LinkedList;
import java.util.StringJoiner;

public class ComplexCommand extends AbstractCommand{

    LinkedList<Object> parameters;

    public ComplexCommand(String command, boolean isRead) {
        super(command, isRead);
    }

    public ComplexCommand(CommandStrings command, boolean isRead) {
        super(command, isRead);
    }

    public boolean addParam(Object param){
        return parameters.add(param);
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
