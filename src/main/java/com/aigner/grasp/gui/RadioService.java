package com.aigner.grasp.gui;

import com.aigner.grasp.JSerialComm.DummySerialSender;
import org.springframework.stereotype.Service;

@Service
public class RadioService {

    private DummySerialSender dummySerialSender = DummySerialSender.getInstance();
    private byte[] device = new byte[]{(byte) 0b00000110};
    private byte[] commandTurnOn = new byte[]{(byte) 0b00000001};
    private byte[] commandTurnOff = new byte[]{(byte) 0b00000010};
    private byte[] commandNextRadioStation = new byte[]{(byte) 0b00000011};
    private byte[] commandLastRadioStation = new byte[]{(byte) 0b00000100};

    public void turnOn() {
        if(dummySerialSender.openPort()) {
            System.out.println("RadioService turnOn(): ");
            dummySerialSender.sendData(dummySerialSender.createMessage(device, commandTurnOn));
        }
    }

    public void turnOff() {
        if(dummySerialSender.openPort()) {
            System.out.println("RadioService turnOff(): ");
            dummySerialSender.sendData(dummySerialSender.createMessage(device, commandTurnOff));
        }
    }

    public void nextRadioStation() {
        if(dummySerialSender.openPort()) {
            System.out.println("RadioService nextRadioStation(): ");
            dummySerialSender.sendData(dummySerialSender.createMessage(device, commandNextRadioStation));
        }
    }

    public void lastRadioStation() {
        if(dummySerialSender.openPort()) {
            System.out.println("RadioService lastRadioStation(): ");
            dummySerialSender.sendData(dummySerialSender.createMessage(device, commandLastRadioStation));
        }
    }

}
