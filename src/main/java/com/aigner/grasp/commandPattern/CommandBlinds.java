package com.aigner.grasp.commandPattern;

public class CommandBlinds implements Command {
    @Override
    public void exectue(byte command, byte item) {
        System.out.println("CommandBlinds exectue");
    }

    @Override
    public void exectue(byte command, byte[] item) {

    }
}