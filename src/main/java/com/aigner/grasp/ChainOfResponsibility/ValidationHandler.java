package com.aigner.grasp.ChainOfResponsibility;

import com.aigner.grasp.CommandPattern.CommandInvoker;

public abstract class ValidationHandler {

    ValidationHandler nextHandler;

    public void setNext(ValidationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected boolean approve(byte[] data) {
        if (validate(data)) {
            if (nextHandler != null) {
                return nextHandler.approve(data);
            } else {
                onSuccess(data);
                return true;
            }
        } else {
            onFailure();
            return false;
        }
    }

    protected abstract boolean validate(byte[] data);

    protected void onSuccess(byte[] data) {
        System.out.println("SUCCESS All validation are aprroved");
        // here we need the next pattern
        CommandInvoker invoker = new CommandInvoker();
        System.out.println("onSuccess: " + data[1]);

        if(data.length > 3) {
            System.out.println("ValidationHandler onSuccess data.length > 2");

            byte[] foodItem = new byte[data.length - 3];
            System.arraycopy(data, 3, foodItem, 0, data.length - 3);
            System.out.println("ValidationHandler foodItem[]");
            for(int i = 0; i < foodItem.length; i++) {
                System.out.print(foodItem[i] + " ");
            }
            System.out.println("\nValidationHandler foodItem[]");

            invoker.executeCommand((byte) data[1], (byte) data[2], foodItem);
        } else {
            System.out.println("ValidationHandler onSuccess data.length <= 2");
            invoker.executeCommand((byte) data[1], (byte) data[2]);
        }
    }

    protected void onFailure() {
        System.out.println("ONFAILURE Validation went wrong");
    }
}
