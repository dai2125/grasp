package com.aigner.grasp.ChainOfResponsibility;

public class ValidationManager {

    ValidationHandler headerHandler = new HeaderHandler();
    ValidationHandler footerHandler = new DeviceHandler();
    ValidationHandler messageHandler = new CommandHandler();

    public ValidationManager() {
        headerHandler.setNext(messageHandler);
        messageHandler.setNext(footerHandler);
    }

    public void validate(byte[] data) {
        headerHandler.approve(data);
    }
}
