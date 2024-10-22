package com.aigner.grasp.CommandPattern;

public class CommandThermostat implements Command {
    @Override
    public void exectue(byte command, byte item) {
        System.out.println("CommandThermostat execute");
    }

    @Override
    public void exectue(byte command, byte[] item) {

    }
}
