package com.aigner.grasp.CommandPattern;

public class CommandClock implements Command {
    @Override
    public void exectue(byte command, byte item) {
        System.out.println("CommandClock exectue");
    }

    @Override
    public void exectue(byte command, byte[] item) {

    }
}
