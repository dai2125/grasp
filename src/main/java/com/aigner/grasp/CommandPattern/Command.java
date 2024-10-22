package com.aigner.grasp.CommandPattern;

import java.util.Optional;

public interface Command {
    void exectue(byte command, byte item);
    void exectue(byte command, byte item[]);
}
