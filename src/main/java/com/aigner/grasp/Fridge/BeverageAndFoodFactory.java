package com.aigner.grasp.Fridge;

import java.util.Date;

public interface BeverageAndFoodFactory {

    Beverage createBeverage(String name, double weight, Date expireDate);
    Food createFood(String name, double weight, Date expireDate);
}
