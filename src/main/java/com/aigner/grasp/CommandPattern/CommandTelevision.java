package com.aigner.grasp.CommandPattern;

public class CommandTelevision implements Command {
    @Override
    public void exectue(byte command, byte item) {
        System.out.println("CommandTelevision exectue");
    }

    @Override
    public void exectue(byte command, byte[] item) {

    }
}
