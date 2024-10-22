package com.aigner.grasp.radio;

public class RadioMemento {

    private String content;

    public RadioMemento(String content) {
        this.content = content;
    }

    public String getSavedContent() {
        return this.content;
    }
}
