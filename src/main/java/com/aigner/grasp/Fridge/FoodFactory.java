package com.aigner.grasp.Fridge;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FoodFactory implements BeverageAndFoodFactory {

    private static final String MILK = "milk";
    private static final String JUICE = "juice";

    private static final String CHEESE = "cheese";
    private static final String EGG = "egg";
    private static final String MEAT = "meat";
    private static final String SAUSAGE = "sausage";
    private static final String YOGHURT = "yoghurt";

    @Override
    public Beverage createBeverage(String name, double weight, Date expireDate) {
        switch(name.toLowerCase()) {
            case MILK:
                return new Milk(name, weight, expireDate);
            case JUICE:
                return new Juice(name, weight, expireDate);
            default:
                return null;
        }
    }

    @Override
    public Food createFood(String name, double weight, Date expireDate) {
        switch(name.toLowerCase()) {
            case CHEESE:
                return new Cheese(name, weight, expireDate);
            case EGG:
                return new Egg(name, weight, expireDate);
            case MEAT:
                return new Meat(name, weight, expireDate);
            case SAUSAGE:
                return new Sausage(name, weight, expireDate);
            case YOGHURT:
                return new Yoghurt(name, weight, expireDate);
            default:
                return null;
        }
    }
}
