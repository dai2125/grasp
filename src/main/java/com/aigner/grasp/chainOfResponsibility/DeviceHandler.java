package com.aigner.grasp.chainOfResponsibility;

public class DeviceHandler extends ValidationHandler {

    private static final byte THERMOSTAT = (byte) 0b00000001;
    private static final byte TELEVISION = (byte) 0b00000100;
    private static final byte FRIDGE = (byte) 0b00000010;
    private static final byte BLINDS = (byte) 0b00000101;
    private static final byte CLOCK = (byte) 0b00000011;
    private static final byte RADIO = (byte) 0b00000110;

    @Override
    protected boolean validate(byte[] data) {
        if(commandValidate(data[1])) {
            System.out.println("DeviceHandler Validation approved");
            if(nextHandler != null) {
                return true;
            }
            return true;
        }
        System.out.println("DeviceHandler message is wrong");
        return false;
    }

    private boolean commandValidate(byte data) {
        return data == THERMOSTAT || data == FRIDGE || data == CLOCK ||
                data == TELEVISION || data == BLINDS || data == RADIO;
    }
}
