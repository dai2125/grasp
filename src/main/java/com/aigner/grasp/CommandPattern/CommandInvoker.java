package com.aigner.grasp.CommandPattern;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {

    private Map<Byte, Command> commandMap = new HashMap<>();

    public CommandInvoker() {
        commandMap.put((byte) 0b00000001, new CommandThermostat()); // Thermostat
        commandMap.put((byte) 0b00000010, new CommandFridge()); // Fridge
        commandMap.put((byte) 0b00000011, new CommandClock()); // Clock
        commandMap.put((byte) 0b00000100, new CommandTelevision()); // Television
        commandMap.put((byte) 0b00000101, new CommandBlinds()); // Blinds
        commandMap.put((byte) 0b00000110, new CommandRadio());

    }

    public void executeCommand(byte befehl, byte item) {
        System.out.println("CommandInvoker executeCommand: " + befehl + " " + item);
        Command command = commandMap.getOrDefault(befehl, new CommandDefault());
        command.exectue(befehl, item);
    }

    public void executeCommand(byte device, byte kommando, byte[] foodItem) {
        Command command = commandMap.getOrDefault(device, new CommandDefault());
        command.exectue(kommando, foodItem);
    }
}
