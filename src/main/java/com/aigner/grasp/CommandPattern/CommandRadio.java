package com.aigner.grasp.CommandPattern;

import com.aigner.grasp.radio.StreamAudio;

public class CommandRadio implements Command {

    StreamAudio streamAudio = StreamAudio.getInstance();

    @Override
    public void exectue(byte command, byte item) {
        System.out.println("CommandRadio execute: " + command + " " + item);
        if(item == 0b00000001) {
            streamAudio.playStream();
        } else if(item == 0b00000010) {
            streamAudio.turnOff();
        } else if(item == 0b00000011) {
            streamAudio.nextRadioStation();
        } else if(item == 0b00000100) {
            streamAudio.lastRadioStation();
        }
    }

    @Override
    public void exectue(byte command, byte[] item) {

    }
}
