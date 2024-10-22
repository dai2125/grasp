package com.aigner.grasp.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioPanel extends JPanel {

    private RadioService radioService;

    private JButton turnOnButton = new JButton("TURN ON");
    private JButton turnOffButton = new JButton("TURN OFF");
    private JButton nextRadioStationButton = new JButton("NEXT");
    private JButton lastRadioStationButton = new JButton("BACK");


    public RadioPanel() {
        radioService = new RadioService();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5, 5, 5, 5));


        JLabel radioLabel = new JLabel("RADIO");
        radioLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(radioLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(lastRadioStationButton);
        buttonPanel.add(turnOffButton);
        buttonPanel.add(turnOnButton);
        buttonPanel.add(nextRadioStationButton);

        add(buttonPanel);

        turnOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnOn();
            }
        });

        turnOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnOff();
            }
        });

        nextRadioStationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextRadioStation();
            }
        });

        lastRadioStationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastRadioStation();
            }
        });
    }

    private void turnOn() {
        radioService.turnOn();
    }

    private void turnOff() {
        radioService.turnOff();
    }

    private void nextRadioStation() {
        radioService.nextRadioStation();
    }

    private void lastRadioStation() {
        radioService.lastRadioStation();
    }
}

