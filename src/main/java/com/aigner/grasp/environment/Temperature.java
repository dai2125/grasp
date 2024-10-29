package com.aigner.grasp.environment;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Temperature {

    Random random = new Random();
    double maxTemperature = 35.0;
    double minTemperature = -10.0;
    double temperature = minTemperature + (maxTemperature - minTemperature) * random.nextDouble();
    private boolean on = true;

    public Temperature() {
        System.out.println("TEMPERATURE constructor(): ");
    }

    @PostConstruct
    public void startTemperature() {
        updateTemperature();
    }

    @Scheduled(fixedRate = 1000)
    public double updateTemperature() {
        temperature += random.nextDouble(-1, 1);
//        System.out.println("TEMPERATURE updateTemperature() random.nextDouble(): " + random.nextDouble());
//        System.out.println("TEMPERATURE updateTemperature(): " + temperature);
        return this.temperature;
    }

    public double getTemperature() {
        return temperature;
    }
}
