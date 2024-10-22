package com.aigner.grasp.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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
