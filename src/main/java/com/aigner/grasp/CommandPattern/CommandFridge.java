package com.aigner.grasp.CommandPattern;

import com.aigner.grasp.Fridge.Beverage;
import com.aigner.grasp.Fridge.FoodFactory;
import com.aigner.grasp.Fridge.Fridge;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class CommandFridge implements Command {

    Fridge fridge = Fridge.getInstance();
    static FoodFactory foodFactory = new FoodFactory();

    @Override
    public void exectue(byte command, byte item) {
        System.out.println("execute()");

        String command2 = Byte.toString(command);
        String item2 = Byte.toString(item);
        System.out.println("CommandFridge execute: " + command2 + " " + item2);
        System.out.println("CommandFridge execute: " + command + " " + item);
//        foodFactory.createFood("Cheese", 120.0, new Date());
        fridge.addItem(foodFactory.createFood("Cheese", 120.0, new Date()));
        fridge.addItem(foodFactory.createBeverage("Milk", 1000.0, new Date()));


    }

    @Override
    public void exectue(byte command, byte[] item) {

        String foodItem = new String(item, StandardCharsets.UTF_8);

        System.out.println("CommandFridge execute: foodItem " + foodItem);
        fridge.addItem(foodFactory.createFood(foodItem, 120.0, new Date()));
        // TODO createBeverage!!!!
    }
}
