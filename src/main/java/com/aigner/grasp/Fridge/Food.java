package com.aigner.grasp.Fridge;

import java.util.Date;

public interface Food {
    void setName(String name);
    String getName();
    void setWeight(double weight);
    double getWeight();
    void setExpireDate(Date date);
    Date getExpireDate();
}
