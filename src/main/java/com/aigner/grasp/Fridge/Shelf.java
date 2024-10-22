package com.aigner.grasp.Fridge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Shelf<T> implements Iterable<T> {

    private int _index;
    private double _capacity;
    private List<T> _items;

    public Shelf(double capacity) {
        _capacity = capacity;
        _items = new ArrayList<>();
    }

    public boolean hasSpace(Food item) {
        if(_capacity - item.getWeight() < 0) {
            return false;
        }
//        System.out.println("capacity: " + _capacity);
        return true;
    }

    public boolean hasSpace(Beverage item) {
        if(_capacity - item.getWeight() < 0) {
            return false;
        }
        return true;
    }

//    public Shelf(int index) {
//        _index = index;
//    }

    public void addItem(Food item) {
//        System.out.println("item: " + item.getName() + " " + item.getWeight() + " " + item.getExpireDate());
        System.out.println("Shelf capacity before: " + _capacity);
        _capacity = _capacity - item.getWeight();
        System.out.println("Shelf capacity after: " + _capacity);

        _items.add((T) item);
        printShelfContents();
//        if(_capacity - item.getWeight() > 0) {
//            _capacity -= item.getWeight();
//            _items.add((T) item);
//            System.out.println("Item added");
//        }
    }

    public void addItem(Beverage item) {
        System.out.println("addItem Beverage item: " + _capacity);
        _capacity = _capacity - item.getWeight();
        System.out.println("addItem Beverage item: " + _capacity);

        _items.add((T) item);
    }

    @Override
    public Iterator<T> iterator() {
//        return new ShelfIterator();
        return new ShelfIterator();
    }

    public void printShelfContents() {
        System.out.println("Inhalt des Shelfs: ");
        for(T item : this) {
            System.out.println(item);
        }
    }

    private class ShelfIterator implements Iterator<T> {

        private int index = 0;
//        private List<T> items;

        @Override
        public boolean hasNext() {
            return index < _items.size();
        }

        @Override
        public T next() {
            if(hasNext()) {
                return _items.get(index++);
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
        }
    }
}
