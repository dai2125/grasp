package com.aigner.grasp.commandPattern;

import com.aigner.grasp.gui.AirCondition;

public class CommandAirCondition implements Command {

    AirCondition airConditioner = AirCondition.getInstance();

    @Override
    public void exectue(byte command, byte item) {
        System.out.println("CommandThermostat execute");
    }

    @Override
    public void exectue(byte command, byte[] item) {
        System.out.println("CommandAirCondition exectue command: " + command + " item: " + item);
        airConditioner.getTemperature(command, item);


    }
}
