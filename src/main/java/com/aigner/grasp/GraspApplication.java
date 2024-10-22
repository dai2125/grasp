package com.aigner.grasp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraspApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraspApplication.class, args);
		System.out.println("hello from the other side...");

//		Singleton object = new Singleton();
	}


}
