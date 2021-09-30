package test.example;

import com.tello.Tello;
import com.tello.connection.functional.command.creator.Reference;
import com.tello.connection.impl.TelloController;


import static com.tello.connection.CommandStrings.COMMAND;
import static com.tello.connection.functional.command.creator.CommandExecutor.execute;

public class Main {
    public static void main(String args[]) throws InterruptedException {

        Reference<String> output = new Reference<>();
        execute(COMMAND).withReadAndSaveIn(output).withParam("test").andThen(execute(COMMAND)).run();

        Tello myTello = new Tello();
        TelloController myTelloController = myTello.getController();
        myTelloController.flyForward((short) 10);
        myTelloController.getBattery();

        myTelloController.takeoff();
        System.out.println(myTelloController.getBattery());
        Thread.sleep(1000);
        myTelloController.land();
        myTello.shutdownGracefully();

    }
}
