package com.aigner.grasp.Fridge;

import java.util.Date;

public interface Beverage {
    void setName(String name);
    String getName();
    void setWeight(double weight);
    double getWeight();
    void setExpireDate(Date expireDate);
    Date getExpireDate();
}
