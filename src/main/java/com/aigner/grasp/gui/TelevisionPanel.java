package com.aigner.grasp.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelevisionPanel extends JPanel {

    public TelevisionPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(5,5,5,5));

        JLabel label = new JLabel("TELEVISION");
        label.setAlignmentX(CENTER_ALIGNMENT);
        add(label);
    }
}
