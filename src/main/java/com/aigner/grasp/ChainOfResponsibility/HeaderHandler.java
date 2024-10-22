package com.aigner.grasp.ChainOfResponsibility;

public class HeaderHandler extends ValidationHandler {

    @Override
    protected boolean validate(byte[] data) {
        if(data[0] == (byte) 0b11111111) {
            System.out.println("HeaderHandler Validation approved");
            if(nextHandler != null) {
                return true;
//                return nextHandler.approve(data);
            }
            return true;
        }
        System.out.println("HeaderHandler header wrong");
        return false;
    }
}
