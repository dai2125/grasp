package com.aigner.grasp.fridge;

import com.aigner.grasp.jSerialComm.DummySerialSender;
import org.springframework.stereotype.Service;

@Service
public class FridgeService {

    DummySerialSender dummySerialSender = DummySerialSender.getInstance();

    private static final byte COMMAND_CREATE = (byte) 0b00000001;
    private static final byte COMMAND_UPDATE = (byte) 0b00000011;
    private static final byte COMMAND_DELETE = (byte) 0b00000100;
    private static final byte COMMAND_READ = (byte) 0b00000010;

    private static final byte THERMOSTAT = (byte) 0b00000001;
    private static final byte TELEVISION = (byte) 0b00000100;
    private static final byte FRIDGE = (byte) 0b00000010;
    private static final byte BLINDS = (byte) 0b00000101;
    private static final byte CLOCK = (byte) 0b00000011;

    private static final byte CHEESE = (byte) 0b00000001;
    private static final byte MEAT = (byte) 0b00000011;
    private static final byte YOGHURT = (byte) 0b00000100;
    private static final byte SAUSAGE = (byte) 0b00000010;
    private static final byte EGG = (byte) 0b00000001;
    private static final byte JUICE = (byte) 0b00000100;
    private static final byte MILK = (byte) 0b00000010;

    public void consumeItem(String foodItem) {
        if(dummySerialSender.openPort()) {
            System.out.println("FridgeService consumeItem: " + foodItem);
            byte[] device = new byte[]{FRIDGE};
            byte[] command = new byte[]{COMMAND_DELETE};
            byte[] item = foodItem.getBytes();
            dummySerialSender.sendData(dummySerialSender.createMessage(device, command, item));
        }
    }

    public void addItem(String foodItem) {
        if(dummySerialSender.openPort()) {
            System.out.println("FridgeService addItem: " + foodItem);
            byte[] device = new byte[]{FRIDGE};
            byte[] command = new byte[]{COMMAND_CREATE};
            byte[] item = foodItem.getBytes();
            dummySerialSender.sendData(dummySerialSender.createMessage(device, command, item));
        }
    }

    public void readFridgeItem() {
        if(dummySerialSender.openPort()) {
            System.out.println("FridgeService readFridgeItem:");
            byte[] device = new byte[]{FRIDGE};
            byte[] command = new byte[]{COMMAND_READ};
            dummySerialSender.sendData(dummySerialSender.createMessage(device, command));
        }
    }
}
