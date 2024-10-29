package com.aigner.grasp.chainOfResponsibility;

public class CommandHandler extends ValidationHandler {

    private static final byte COMMAND_CREATE = (byte) 0b00000001;
    private static final byte COMMAND_UPDATE = (byte) 0b00000011;
    private static final byte COMMAND_DELETE = (byte) 0b00000100;
    private static final byte COMMAND_READ = (byte) 0b00000010;

    @Override
    protected boolean validate(byte[] data) {
       if(commandValidate(data[2])) {
            System.out.println("CommandHandler Validation approved");
            if(nextHandler != null) {
                return true;
//                return nextHandler.approve(data);
            }
            return true;
        }
        System.out.println("CommandHandler validation is wrong " + data[2] + " " + data.length);
        return false;
    }

    private boolean commandValidate(byte data) {
        return data == COMMAND_CREATE || data == COMMAND_READ || data == COMMAND_UPDATE || data == COMMAND_DELETE;
    }
}

/*  CRUD
*   create
*   read
*   update
*   delete
*/

/*
* Cheese    0000 0001
* Egg       0000 0010
* Meat      0000 0011
* Sausage   0000 0100
* Yoghurt   0000 0101
* Juice     0000 0110
* Milk      0000 0111
* */
