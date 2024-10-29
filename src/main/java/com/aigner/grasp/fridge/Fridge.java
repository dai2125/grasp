package com.aigner.grasp.fridge;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class Fridge {

    private static Fridge instance;
    private boolean _doorIsOpen;
    private boolean _lightIsOn;
    private double _firstShelfCapacity;
    private double _secondShelfCapacity;
    private double _vegetableShelfCapacity;
    private double _sideShelfCapacity;
    private List<Food> itemsFirstShelf;
    private List<Food> itemsSecondShelf;
    private List<Food> itemsVegetableShelf;
    private List<Beverage> itemsSideShelf;

    private final Shelf fridgeFirstShelf;
    private final Shelf fridgeSecondShelf;
    private final Shelf fridgeSideShelf;
    private final Shelf fridgeVegetableShelf;

    public Fridge() {
        _doorIsOpen = false;
        _lightIsOn = false;
        _firstShelfCapacity = 10;
        _secondShelfCapacity = 10;
        _vegetableShelfCapacity = 15;
        _sideShelfCapacity = 5;
        fridgeFirstShelf = new Shelf(7000);
        fridgeSecondShelf = new Shelf(7000);
        fridgeVegetableShelf = new Shelf(4000);
        fridgeSideShelf = new Shelf(5000);
    }

    public static Fridge getInstance() {
        if(instance == null) {
            instance = new Fridge();
        }
        return instance;
    }

    public void setDoorIsOpen(boolean doorIsOpen) {
        _doorIsOpen = doorIsOpen;
    }

    public boolean getDoorIsOpen() {
        return _doorIsOpen;
    }

    public void setLightIsOn(boolean lightIsOn) {
        _lightIsOn = lightIsOn;
    }

    public boolean getLightIsOn() {
        return _lightIsOn;
    }

    public void addItem(Food item) {
        if(fridgeFirstShelf.hasSpace(item)) {
            System.out.println("fridgeFirstShelf.hasSpace");
            fridgeFirstShelf.addItem(item);
        } else if(fridgeSecondShelf.hasSpace(item)) {
            System.out.println("fridgeSecondShelf.hasSpace");
            fridgeSecondShelf.addItem(item);
        } else if(fridgeVegetableShelf.hasSpace(item)) {
            System.out.println("fridgeVegetableShelf.hasSpace");
            fridgeVegetableShelf.addItem(item);
        } else {
            System.out.println("Fridge addItem Food: kein Platz für weitere Artikel");
        }

        System.out.println("Fridge addItem Food item added: " + item.getName());
    }

    public void addItem(Beverage item) {
        System.out.println("item: " + item.getName() + " " + item.getWeight() + " " + item.getExpireDate());
        System.out.println("_sideShelfCapacity: " + _sideShelfCapacity);


        if(fridgeSideShelf.hasSpace(item)) {
            fridgeSideShelf.addItem(item);
        } else {
            System.out.println("Fridge addItem Beverage: kein Platz für weitere Artikel");
        }

        System.out.println("Fridge addItem Beverage item added: " + item.getName());
    }

    public void consumeItem(Food item) {


    }

    public void consumeItem(Beverage item) {


    }

    public boolean addFoodItem(Food item) {
        if(_firstShelfCapacity - item.getWeight() > 0) {
            _firstShelfCapacity -= item.getWeight();
            return true;
        } else if(_secondShelfCapacity - item.getWeight() > 0) {
            _secondShelfCapacity -= item.getWeight();
            return true;
        } else {
            return false;
        }
    }

    public boolean addBeverageItem(Beverage item) {
        if(_sideShelfCapacity - 1 > 0) {
            _sideShelfCapacity -= 1;
            return true;
        }
        return false;
    }
}
