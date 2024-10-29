package com.aigner.grasp.sensor;

import com.aigner.grasp.environment.Temperature;
import com.aigner.grasp.gui.AirConditionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperatureSensor {

    private final AirConditionerService airConditionerService;
    private final Temperature temperature;

    @Autowired
    public TemperatureSensor(AirConditionerService airConditionerService, Temperature temperature) {
        this.airConditionerService = airConditionerService;
        this.temperature = temperature;
        test();
    }

//    @Scheduled(fixedRate = 500)
    public void test() {
        while(true) {
            try {
                Thread.sleep(200000);
                System.out.println("TEMPERATURESENSOR test() 2000ms: " + temperature.updateTemperature());
                airConditionerService.sendTemperature(temperature.getTemperature());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
