package com.aigner.grasp.gui;

import org.springframework.stereotype.Service;

@Service
public class AirCondition {

    private static AirCondition instance;

    public AirCondition() {

    }

    public static AirCondition getInstance() {
        if(instance == null) {
            instance = new AirCondition();
        }
        return instance;
    }

    public void getTemperature(byte command, byte[] item) {

        double temperature = (double) item[0];
        if(temperature > 21) {
            System.out.println("AirCondition getTemperature: temperature > 21: " + temperature);
            coolDown(temperature);
        } else if(temperature < 16) {
            System.out.println("AirCondition getTemperature: temperature < 16: " + temperature);
            heatUp(temperature);
        } else {
            System.out.println("AirCondition getTemperature: temperature between 21 and 16: " + temperature);
        }
    }

    private void coolDown(double temperature) {
        System.out.println("AirCondition coolDown(): " + temperature);
    }

    private void heatUp(double temperature) {
        System.out.println("AirCondition heatUp(): " + temperature);

    }

}
