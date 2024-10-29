package com.aigner.grasp;

import com.aigner.grasp.environment.Temperature;
import com.aigner.grasp.sensor.TemperatureSensor;
import com.aigner.grasp.gui.InputMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraspApplication {

	private final TemperatureSensor temperatureSensor;
	private final InputMachine inputMachine;
	private final Temperature temperature;

	@Autowired
	public GraspApplication(TemperatureSensor temperatureSensor, InputMachine inputMachine, Temperature temperature) {
		this.temperatureSensor = temperatureSensor;
		this.inputMachine = inputMachine;
		this.temperature = temperature;
	}

	public static void main(String[] args) {
		System.out.println("hello from the other side...");

		InputMachine inputMachine = new InputMachine();
		SpringApplication.run(GraspApplication.class, args);
	}
}
