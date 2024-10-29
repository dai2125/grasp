package com.aigner.grasp.chainOfResponsibility;

public class HeaderHandler extends ValidationHandler {

    private static final byte KEY = (byte) 0b11111111;

    @Override
    protected boolean validate(byte[] data) {
        if(commandValidate(data[0])) {
            System.out.println("HeaderHandler Validation approved");
            if(nextHandler != null) {
                return true;
            }
            return true;
        }
        System.out.println("HeaderHandler header wrong");
        return false;
    }

    private boolean commandValidate(byte data) {
        return data == KEY;
    }
}
