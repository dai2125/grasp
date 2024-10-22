package com.aigner.grasp.Fridge;

import com.aigner.grasp.JSerialComm.DummySerialSender;
import org.springframework.stereotype.Service;

@Service
public class FridgeService {

    DummySerialSender dummySerialSender = DummySerialSender.getInstance();
    byte[] thermostat = new byte[]{(byte) 0b00000001};
    byte[] fridge = new byte[]{(byte) 0b00000010};
    byte[] clock = new byte[]{(byte) 0b00000011};
    byte[] television = new byte[]{(byte) 0b00000100};
    byte[] blinds = new byte[]{(byte) 0b00000101};
    byte[] fridgeAdd = new byte[]{(byte) 0b00000001};
    byte[] fridgeConsume = new byte[]{(byte) 0b00000010};
    byte[] test = new byte[]{(byte) 0b11111111};
    byte[] cheese = new byte[]{(byte) 0b00000001};
    byte[] egg = new byte[]{(byte) 0b00000010};
    byte[] meat = new byte[]{(byte) 0b00000011};
    byte[] sausage = new byte[]{(byte) 0b00000100};
    byte[] yoghurt = new byte[]{(byte) 0b00000101};
    byte[] juice = new byte[]{(byte) 0b00000110};
    byte[] milk = new byte[]{(byte) 0b00000111};

    public void consumeItem(String foodItem) {
        if(dummySerialSender.openPort()) {
            // TODO hier weitermachen
            // TODO du brauchst zuerst ein system für die items, wie sie in bytes dargestellt werden sollen, weil jetzt sendest du test oder String konvertierte bytes die die Validation nicht kennt.
            System.out.println("FridgeService consumeItem: " + foodItem);
            byte[] device = fridge;
            byte[] command = new byte[]{(byte) 0b01000001};
            byte[] item = foodItem.getBytes();
//            test = foodItem.getBytes();
            dummySerialSender.sendData(dummySerialSender.createMessage(device, command, item));
        }
    }
}
