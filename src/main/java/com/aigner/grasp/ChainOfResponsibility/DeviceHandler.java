package com.aigner.grasp.ChainOfResponsibility;

public class DeviceHandler extends ValidationHandler {

    @Override
    protected boolean validate(byte[] data) {
        if(data[1] == (byte) 0b00000001 || data[1] == (byte) 0b00000010 ||
            data[1] == (byte) 0b00000011 || data[1] == (byte) 0b00000100 ||
            data[1] == (byte) 0b00000101 || data[1] == (byte) 0b00000110) {
            System.out.println("DeviceHandler Validation approved");
            if(nextHandler != null) {
                return true;
//                return nextHandler.approve(data);
            }
            return true;
        }
        System.out.println("DeviceHandler message is wrong");
        return false;
    }
}
