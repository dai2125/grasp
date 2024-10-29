package com.aigner.grasp.gui;

import com.aigner.grasp.fridge.FridgeService;
import com.aigner.grasp.jSerialComm.DummySerialReceiver;
import com.aigner.grasp.jSerialComm.DummySerialSender;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputMachine {

    Scanner scanner = new Scanner(System.in);
    private DummySerialSender serialSender = DummySerialSender.getInstance();
    private DummySerialReceiver serialReceiver;
    private FridgeService fridgeService;
    private RadioService radioService;
    private AirConditionerService airConditionerService;

    public InputMachine() {

        serialReceiver = new DummySerialReceiver();
        fridgeService = new FridgeService();
        radioService = new RadioService();
        airConditionerService = new AirConditionerService();

        start();

        if (serialReceiver.openPort()) {
            serialReceiver.start();
        } else {
            System.err.println("Konnte den seriellen Port nicht öffnen.");
        }

//        new Thread(() -> {
//            while (true) {
//                System.out.println("Enter FRIDGE TELEVISION RADIO");
//                String input = scanner.nextLine();
//                System.out.println("Input was ... " + input);
//                if(input.equalsIgnoreCase("FRIDGE")) {
//                    // TODO FRIDGE MENU
//                    fridgeMenu();
//                } else if(input.equalsIgnoreCase("RADIO")) {
//                    // TODO RADIO MENU
//                    radioMenu();
//                }
//            }
//        }).start();
    }

    private void start() {
        new Thread(() -> {
            while (true) {
                System.out.println("Enter FRIDGE TELEVISION RADIO");
                String input = inputTrim(scanner.nextLine());
                System.out.println("Input was ... " + input);
                if(input.equalsIgnoreCase("FRIDGE")) {
                    // TODO FRIDGE MENU
                    fridgeMenu();
                } else if(input.equalsIgnoreCase("RADIO")) {
                    // TODO RADIO MENU
                    radioMenu();
                }
            }
        }).start();
    }

    private void fridgeMenu() {
        System.out.println("FRIDGE MENU\nCONSUME ADD");
        String input = inputTrim(scanner.nextLine());
        if(input.equalsIgnoreCase("CONSUME")) {
            System.out.println("FRIDGE MENU CONSUME\nCHEESE EGG SAUSAGE MEAT YOGHURT MILK JUICE");
            input = inputTrim(scanner.nextLine());
            fridgeService.consumeItem(input);
        } else if(input.equalsIgnoreCase("ADD")) {
            System.out.println("FRIDGE MENU ADD\nCHEESE EGG SAUSAGE MEAT YOGHURT MILK JUICE");
            input = inputTrim(scanner.nextLine());
            fridgeService.addItem(input);
        }
    }

    private void radioMenu() {
        System.out.println("LAST START STOP NEXT");
        String input = inputTrim(scanner.nextLine());
        if(input.equalsIgnoreCase("LAST")) {
            System.out.println("RADIO LAST PRESSED");
            radioService.lastRadioStation();
        } else if(input.equalsIgnoreCase("START")) {
            System.out.println("RADIO LAST START");

            radioService.turnOn();
        } else if(input.equalsIgnoreCase("STOP")) {
            System.out.println("RADIO STOP PRESSED");

            radioService.turnOff();
        } else if(input.equalsIgnoreCase("NEXT")) {
            System.out.println("RADIO NEXT PRESSED");

            radioService.nextRadioStation();
        }
    }

    private String inputTrim(String input) {
        return input.trim();
    }


}
