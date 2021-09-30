package com.tello.connection.functional.command.creator;

import com.tello.connection.CommandStrings;
import com.tello.connection.Connection;
import com.tello.connection.functional.command.model.BasicCommand;
import com.tello.connection.functional.command.model.Command;
import com.tello.connection.functional.command.model.ComplexCommand;
import com.tello.connection.impl.TelloController;

import java.util.Optional;

public abstract class CommandExecutor {

    protected static Connection connection;

    protected Command command;

    protected CommandExecutor after;

    protected Reference<String> reference;

    public static CommandExecutor execute(CommandStrings command) {
        return new BasicExecutor(command);
    }

    public static CommandExecutor execute(String command) {
        return new BasicExecutor(command);
    }

    public abstract CommandExecutor withParam(Object object);

    public CommandExecutor withReadAndSaveIn(Reference<String> reference) {
        command.setRead(true);
        this.reference = reference;
        return this;
    }

    public void createConnection() {
        if (connection == null) {
            connection = new Connection(TelloController.host, TelloController.port);
        }
    }


    public void run() {
        if (command.isRead()) {
            String answer = connection.sendAndReceiveCommand(command.compose());
            reference.setReference(answer);
        }else {
            connection.sendCommand(command.compose());
        }
        after.run();
    }

    public CommandExecutor andThen(CommandExecutor executor) {
        this.after = executor;
        return this;
    }

    private static class BasicExecutor extends CommandExecutor {
        private BasicExecutor(String command) {
            this.command = new BasicCommand(command, false);
        }

        private BasicExecutor(CommandStrings commandStrings) {
            this(commandStrings.getCommand());
        }

        @Override
        public CommandExecutor withParam(Object object) {
            CommandExecutor executor = new ComplexExecutor(this);
            executor.withParam(object);
            return executor;
        }

        @Override
        public void run() {
            super.run();
        }
    }

    private static class ComplexExecutor extends CommandExecutor {
        private ComplexCommand command;

        public ComplexExecutor(BasicExecutor basicExecutor) {
            this.command = new ComplexCommand((BasicCommand) basicExecutor.command);
        }

        @Override
        public CommandExecutor withParam(Object object) {
            command.addParam(object);
            return this;
        }

        @Override
        public void run() {
            super.run();
        }
    }
}
