package com.aigner.grasp.Fridge;

import java.util.Date;

public class Yoghurt implements Food {

    private String _name;
    private double _weight;
    private Date _expireDate;

    public Yoghurt(String name, double weight, Date expireDate) {
        _name = name;
        _weight = weight;
        _expireDate = expireDate;
    }

    @Override
    public void setName(String name) {
        _name = name;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setWeight(double weight) {
        _weight = weight;
    }

    @Override
    public double getWeight() {
        return _weight;
    }

    @Override
    public void setExpireDate(Date date) {
        _expireDate = date;
    }

    @Override
    public Date getExpireDate() {
        return _expireDate;
    }
}
