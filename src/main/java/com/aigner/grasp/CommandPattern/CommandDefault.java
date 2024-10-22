package com.aigner.grasp.CommandPattern;

public class CommandDefault implements Command {
    @Override
    public void exectue(byte command, byte item) {
        System.out.println("CommandDefault execute");
    }

    @Override
    public void exectue(byte command, byte[] item) {

    }
}
