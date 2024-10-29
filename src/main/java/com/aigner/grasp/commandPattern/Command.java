package com.aigner.grasp.commandPattern;

public interface Command {
    void exectue(byte command, byte item);
    void exectue(byte command, byte item[]);
}
