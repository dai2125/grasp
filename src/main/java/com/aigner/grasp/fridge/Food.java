package com.aigner.grasp.fridge;

import java.util.Date;

public abstract class Food {
    protected String _name;
    protected double _weight;
    protected Date _expireDate;

    protected Food(String name, double weight, Date expireDate) {
        _name = name;
        _weight = weight;
        _expireDate = expireDate;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void setWeight(double weight) {
        _weight = weight;
    }

    public double getWeight() {
        return _weight;
    }

    public void setExpireDate(Date expireDate) {
        _expireDate = expireDate;
    }

    public Date getExpireDate() {
        return _expireDate;
    }
}
