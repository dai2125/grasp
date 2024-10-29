package com.aigner.grasp.gui;

import com.aigner.grasp.jSerialComm.DummySerialSender;
import com.aigner.grasp.jSerialComm.DummySerialReceiver;
import com.aigner.grasp.statePattern.EastState;
import com.aigner.grasp.statePattern.WestState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GamePanel extends JPanel {

    private Image imageHome;
    private Image imagePlayer;
    private BufferedImage imageHomeBorder;
    private BufferedImage bufferedImagePlayer;
    private Player player;
    private DummySerialSender serialSender = DummySerialSender.getInstance();
    private DummySerialReceiver serialReceiver;

    public GamePanel() {
//        imageHome = new ImageIcon("../resources/images/home.png").getImage();

        URL imageUrl = getClass().getResource("/images/home.png");
        URL imageUrlPlayer = getClass().getResource("/images/player_south_straight.png");
        URL imageUrlHomeBorder = getClass().getResource("/images/home_border.png");

        if(imageUrl != null) {
            imageHome = new ImageIcon(imageUrl).getImage();
//            try {
//                imageHome = ImageIO.read(getClass().getResourceAsStream("/images/home_border.png"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        } else {
            System.err.println("Bild konnte nicht geladen werden");
        }
        if(imageUrlPlayer != null) {
            imagePlayer = new ImageIcon(imageUrlPlayer).getImage();
            try {
                bufferedImagePlayer = ImageIO.read(getClass().getResourceAsStream("/images/player_west_walk.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(imageUrlHomeBorder != null) {
            try {
                imageHomeBorder = ImageIO.read(getClass().getResourceAsStream("/images/home_border.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        try {
//            BufferedImage imageHomeBuffered = ImageIO.read(new File("/images/home.png"));
//            int width = imageHomeBuffered.getWidth();
//            int height = imageHomeBuffered.getHeight();
//
//            int[][] arr = new int[height][width];
//
//            for(int i = 0; i < 2048; i++) {
//                for(int j = 0; j < 2048; j++) {
//                    arr[i][j] = imageHomeBuffered.getRGB(i, j);
//                    System.out.print(arr[i][j]);
//                }
//                System.out.println();
//            }
//
//        } catch (Exception exception) {
//
//        }



        player = new Player(350, 200, imagePlayer);
//        player = new Player(350, 200, bufferedImagePlayer);
//        bufferedImagePlayer.setRGB(0, 0, Color.BLACK.getRGB());
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
                if(borderWest(player.getX() - 5, player.getY())) {

                    player.move(-5, 0);
                }
//                System.out.println(player.getX() + " " + player.getY());
                break;
            case KeyEvent.VK_RIGHT:
                if(!(player.getState() instanceof EastState)) {
                    player.setState(new EastState());
                }
                if(borderEast(player.getX() + 5, player.getY())) {

                    player.move(5, 0);
                }

//                System.out.println(player.getX() + " " + player.getY());
                break;
            case KeyEvent.VK_UP:
                if(!(player.getState() instanceof NorthState)) {
                    player.setState(new NorthState());
                }
                if(borderNorth(player.getX(), player.getY() - 5)) {

                    player.move(0,- 5);
                }
//                System.out.println(player.getX() + " " + player.getY());
                break;
            case KeyEvent.VK_DOWN:
                if(!(player.getState() instanceof SouthState)) {
                    player.setState(new SouthState());
                }
                if(borderSouth(player.getX() , player.getY() + 5 )) {

                    player.move(0, 5);
                }
//                System.out.println(player.getX() + " " + player.getY());
                break;
            case KeyEvent.VK_SPACE:
//                player.interact(player.getX(), player.getY());

//                serialSender.sendData(serialSender.createMessage(player.interact(player.getX(), player.getY())));

//                new DialogPanel();
                JOptionPane.showMessageDialog(null, new DialogPanel());
//                JOptionPane.showMessageDialog(null, new RadioPanel());

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

    private boolean borderNorth(int x, int y) {
        System.out.println("Player x: " + x +  " y: " + y);
        Color color = new Color(imageHomeBorder.getRGB(x , y));
        Color color2 = new Color(imageHomeBorder.getRGB(x + 49, y));

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int red2 = color2.getRed();
        int green2 = color2.getGreen();
        int blue2 = color2.getBlue();
        if(red == 247 && green == 231 && blue == 214 || red == 255 && green == 156 && blue == 198 ||
                red2 == 247 && green2 == 231 && blue2 == 214 || red2 == 255 && green2 == 156 && blue2 == 198) {
            System.out.println("borderNorth Fußboden");
            return true;
        }
        System.out.println("borderNorth Kein Fußboden");
        return false;
    }

    private boolean borderSouth(int x, int y) {
        Color color = new Color(imageHomeBorder.getRGB(x, y));
        Color color2 = new Color(imageHomeBorder.getRGB(x , y ));

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int red2 = color2.getRed();
        int green2 = color2.getGreen();
        int blue2 = color2.getBlue();
        if(red == 247 && green == 231 && blue == 214 || red == 255 && green == 156 && blue == 198) {
//                ||
//                red2 == 247 && green2 == 231 && blue2 == 214 || red2 == 255 && green2 == 156 && blue2 == 198) {
            System.out.println("borderSouth Fußboden");
            return true;
        }
        System.out.println("borderSouth Kein Fußboden");
        return false;
    }

    private boolean borderEast(int x, int y) {
        Color color = new Color(imageHomeBorder.getRGB(x, y));
        Color color2 = new Color(imageHomeBorder.getRGB(x, y ));

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int red2 = color2.getRed();
        int green2 = color2.getGreen();
        int blue2 = color2.getBlue();
        if(red == 247 && green == 231 && blue == 214 || red == 255 && green == 156 && blue == 198) {
//                ||
//                red2 == 247 && green2 == 231 && blue2 == 214 || red2 == 255 && green2 == 156 && blue2 == 198) {
            System.out.println("borderEast Fußboden");
            return true;
        }
        System.out.println("borderEast Kein Fußboden");
        return false;
    }

    private boolean borderWest(int x, int y) {
        Color color = new Color(imageHomeBorder.getRGB(x , y));
        Color color2 = new Color(imageHomeBorder.getRGB(x , y ));

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int red2 = color2.getRed();
        int green2 = color2.getGreen();
        int blue2 = color2.getBlue();
        if(red == 247 && green == 231 && blue == 214 || red == 255 && green == 156 && blue == 198) {
//                ||
//                red2 == 247 && green2 == 231 && blue2 == 214 || red2 == 255 && green2 == 156 && blue2 == 198) {
            System.out.println("borderWest Fußboden");
            return true;
        }
        System.out.println("borderWest Kein Fußboden");
        return false;
    }
}
