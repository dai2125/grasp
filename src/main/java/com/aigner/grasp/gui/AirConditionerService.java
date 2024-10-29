package com.aigner.grasp.gui;

import com.aigner.grasp.jSerialComm.DummySerialSender;
import org.springframework.stereotype.Service;

@Service
public class AirConditionerService {

    private DummySerialSender dummySerialSender = DummySerialSender.getInstance();
    private byte[] device = new byte[]{(byte) 0b00000001};

    private static final byte COMMAND_CREATE = (byte) 0b00000001;
    private static final byte[] COMMAND_UPDATE = new byte[]{(byte) 0b00000011};
    private static final byte COMMAND_DELETE = (byte) 0b00000100;
    private static final byte COMMAND_READ = (byte) 0b00000010;

    private byte[] commandTurnOn = new byte[]{(byte) 0b00000001};
    private byte[] commandTurnOff = new byte[]{(byte) 0b00000010};
    private byte[] commandNextRadioStation = new byte[]{(byte) 0b00000011};
    private byte[] commandLastRadioStation = new byte[]{(byte) 0b00000100};

    public void sendTemperature(double temperatureValue) {
        if(dummySerialSender.openPort()) {
            byte[] temperature = new byte[] {(byte) temperatureValue};
            System.out.println("AirConditionService sendTemperarture(): " + temperature + " " + temperatureValue);
            dummySerialSender.sendData(dummySerialSender.createMessage(device, COMMAND_UPDATE, temperature));
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
