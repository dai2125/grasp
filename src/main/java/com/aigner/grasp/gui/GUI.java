package com.aigner.grasp.gui;

import org.springframework.stereotype.Component;

import javax.swing.*;

public class GUI {

    private JFrame window;

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        createWindow();
        window.add(new GamePanel());
        window.setVisible(true);
    }

    public void createWindow() {
        window = new JFrame("Game boy");
        window.setSize(554, 582);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
