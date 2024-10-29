package com.aigner.grasp.fridge;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FridgeSecondShelf implements Iterable<Food> {

    private double _capacity = 7000;
    private List<Food> _items = new ArrayList<>();

    public FridgeSecondShelf() {

    }

    public void addItem(Food item) {
        if(_capacity -item.getWeight() > 0) {
            _items.add(item);
            _capacity -= item.getWeight();
        }
    }

    public void removeItem(Food item) {
        _items.remove(item);
        _capacity += item.getWeight();
    }

    @Override
    public Iterator<Food> iterator() {
        return new FridgeSecondShelfIterator(_items);
    }

    private class FridgeSecondShelfIterator implements Iterator<Food> {
        private int _index;
        private List<Food> _items;

        public FridgeSecondShelfIterator(List<Food> items) {
            _items = items;
        }

        @Override
        public boolean hasNext() {
            return _index < _items.size();
        }

        @Override
        public Food next() {
            if(hasNext()) {
                return _items.get(_index++);
            }
            return null;
        }
    }
}
