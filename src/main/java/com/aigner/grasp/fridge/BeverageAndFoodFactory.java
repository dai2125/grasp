package com.aigner.grasp.fridge;

import java.util.Date;

public interface BeverageAndFoodFactory {

    Beverage createBeverage(String name, double weight, Date expireDate);
    Food createFood(String name, double weight, Date expireDate);
}
