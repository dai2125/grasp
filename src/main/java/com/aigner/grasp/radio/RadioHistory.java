package com.aigner.grasp.radio;

import java.util.ArrayList;
import java.util.List;

public class RadioHistory {

    public List<RadioMemento> radioMementoList;

    public RadioHistory() {
        this.radioMementoList = new ArrayList<>();
    }

    public void addMemento(RadioMemento memento) {
        System.out.println("RadioHistory addMemento(): " + radioMementoList.size());

        this.radioMementoList.add(memento);
        System.out.println("RadioHistory addMemento(): " + radioMementoList.getLast());
    }

    public RadioMemento getMemento(int index) {
        return this.radioMementoList.get(index);
    }
}
