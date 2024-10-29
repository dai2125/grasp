package com.aigner.grasp.gui;

import com.aigner.grasp.fridge.FridgeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogPanel extends JPanel {

    private CardLayout cardLayout;
    private FridgeService fridgeService;

    JTextField consumeTextField = new JTextField(15);
    JButton consumeSubmitButton = new JButton("Submit Consume");

    JTextField addTextField = new JTextField(15);

    public DialogPanel() {
        fridgeService = new FridgeService();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5,5,5,5));

        JPanel buttonPanel = new JPanel(new GridLayout(4,1,5,5));

        JLabel fridgeLabel = new JLabel("FRIDGE");
        JRadioButton consumeButton = new JRadioButton("CONSUME");
        JRadioButton addButton = new JRadioButton("ADD");
        JRadioButton cancelButton = new JRadioButton("CANCEL");

        buttonPanel.add(fridgeLabel);
        buttonPanel.add(consumeButton);
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);

        JPanel consumeCard = new JPanel();
        consumeCard.add(new JLabel("This is the consume dialog"));
        consumeTextField = new JTextField(15);
        consumeSubmitButton = new JButton("Submit Consume");
        consumeSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendConsumeToFridge();
            }
        });
        consumeCard.add(consumeTextField);
        consumeCard.add(consumeSubmitButton);

        JPanel addCard = new JPanel();
        addCard.add(new JLabel("This is the add dialog"));
        addTextField = new JTextField(15);
        addCard.add(addTextField);
        JButton addSubmitButton = new JButton("Submit Add");
        addSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendAddToFridge();
            }
        });
        addCard.add(addSubmitButton);

        cards.add(consumeCard, "consume");
        cards.add(addCard, "add");

        add(buttonPanel, BorderLayout.NORTH);
        add(cards, BorderLayout.CENTER);


        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(consumeButton.isSelected()) {
                    System.out.println("consume");
                    cardLayout.show(cards, "consume");
//                    cardLayout.next(addCard);
                } else if(addButton.isSelected()) {
                    System.out.println("add");
//                    cardLayout.next(consumeCard);
                    cardLayout.show(cards, "add");
                } else if(cancelButton.isSelected()) {
                    System.out.println("cancel");
//                    close();
                }
            }
        };

        consumeButton.addActionListener(actionListener);
        addButton.addActionListener(actionListener);

        ButtonGroup group = new ButtonGroup();
        group.add(consumeButton);
        group.add(addButton);
        group.add(cancelButton);
    }

    private void sendConsumeToFridge() {
        String item = consumeTextField.getText();
        System.out.println("DialogPanel sendConsumeToFridge(): " + item);
        fridgeService.consumeItem(item);
    }

    private void sendAddToFridge() {
        String item = addTextField.getText();
        System.out.println("DialogPanel sendAddToFridge(): " + item);
        fridgeService.addItem(item);
    }
}
