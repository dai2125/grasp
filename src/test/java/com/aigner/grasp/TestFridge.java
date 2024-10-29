package com.aigner.grasp;

import com.aigner.grasp.fridge.Fridge;
import com.aigner.grasp.jSerialComm.DummySerialSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestFridge {

    @Autowired
    private Fridge fridge;

    @Test
    public void testFridge() {
        byte[] fridge = new byte[]{(byte) 0b00000010};
        byte[] fridgeAdd = new byte[]{(byte) 0b00000001};
        byte[] cheese = new byte[]{(byte) 0b00000001};
        byte[] egg = new byte[]{(byte) 0b00000010};
        byte[] meat = new byte[]{(byte) 0b00000011};
        byte[] sausage = new byte[]{(byte) 0b00000100};
        byte[] yoghurt = new byte[]{(byte) 0b00000101};
        byte[] juice = new byte[]{(byte) 0b00000110};
        byte[] milk = new byte[]{(byte) 0b00000111};

        DummySerialSender sender = new DummySerialSender();
        sender.createMessage(fridge, fridgeAdd, meat);
    }
}
