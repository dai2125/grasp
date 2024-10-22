package com.aigner.grasp.gui;

import com.aigner.grasp.JSerialComm.DummySerialSender;
import com.aigner.grasp.JSerialComm.DummySerialReceiver;
import com.aigner.grasp.StatePattern.EastState;
import com.aigner.grasp.StatePattern.HungryState;
import com.aigner.grasp.StatePattern.TiredState;
import com.aigner.grasp.StatePattern.WestState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class GamePanel extends JPanel {

    private Image imageHome;
    private Image imagePlayer;
    private Player player;
    private DummySerialSender serialSender = DummySerialSender.getInstance();
    private DummySerialReceiver serialReceiver;

    public GamePanel() {
//        imageHome = new ImageIcon("../resources/images/home.png").getImage();

        URL imageUrl = getClass().getResource("/images/home.png");
        URL imageUrlPlayer = getClass().getResource("/images/player_south_straight.png");

        if(imageUrl != null) {
            imageHome = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Bild konnte nicht geladen werden");
        }
        if(imageUrlPlayer != null) {
            imagePlayer = new ImageIcon(imageUrlPlayer).getImage();
        }

        try {
            BufferedImage imageHomeBuffered = ImageIO.read(new File("/images/home.png"));
            int width = imageHomeBuffered.getWidth();
            int height = imageHomeBuffered.getHeight();

            int[][] arr = new int[height][width];

            for(int i = 0; i < 2048; i++) {
                for(int j = 0; j < 2048; j++) {
                    arr[i][j] = imageHomeBuffered.getRGB(i, j);
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }

        } catch (Exception exception) {

        }



        player = new Player(100, 100, imagePlayer);
        // TODO STATE PATTERN
//        player.setState(new NormalState());

//        serialSender = new DummySerialSender();
        if (!serialSender.openPort()) {
            System.err.println("Konnte den seriellen Port nicht öffnen.");
        }

        serialReceiver = new DummySerialReceiver();
        if (serialReceiver.openPort()) {
            serialReceiver.start();
        } else {
            System.err.println("Konnte den seriellen Port nicht öffnen.");
        }

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if(!(player.getState() instanceof WestState)) {
                    player.setState(new WestState());
                }

                player.move(-5, 0);
//                System.out.println(player.getX() + " " + player.getY());
                break;
            case KeyEvent.VK_RIGHT:
                if(!(player.getState() instanceof EastState)) {
                    player.setState(new EastState());
                }
                player.move(5, 0);
//                System.out.println(player.getX() + " " + player.getY());
                break;
            case KeyEvent.VK_UP:
                if(!(player.getState() instanceof NorthState)) {
                    player.setState(new NorthState());
                }
                player.move(0, -5);
//                System.out.println(player.getX() + " " + player.getY());
                break;
            case KeyEvent.VK_DOWN:
                if(!(player.getState() instanceof SouthState)) {
                    player.setState(new SouthState());
                }
                player.move(0, 5);
//                System.out.println(player.getX() + " " + player.getY());
                break;
            case KeyEvent.VK_SPACE:
//                player.interact(player.getX(), player.getY());

//                serialSender.sendData(serialSender.createMessage(player.interact(player.getX(), player.getY())));

//                new DialogPanel();
//                JOptionPane.showMessageDialog(null, new DialogPanel());
                JOptionPane.showMessageDialog(null, new RadioPanel());

                break;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if(imageHome != null) {
            graphics.drawImage(imageHome, 0, 0, this);
        }

        player.draw(graphics);
    }

    public void close() {
        serialSender.closePort();
        serialReceiver.stop();
        serialReceiver.closePort();
    }
}
