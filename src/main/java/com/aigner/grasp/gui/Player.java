package com.aigner.grasp.gui;

import com.aigner.grasp.statePattern.PlayerState;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Player {

    private int _x;
    private int _y;
    private Image _image;
    private PlayerState _state;
    private Image _eastStand;
    private Image _eastWalk;
    private Image _northStand;
    private Image _northLeft;
    private Image _northRight;
    private Image _southLeft;
    private Image _southRight;
    private Image _southStand;
    private Image _westStand;
    private Image _westWalk;
//    URL imageUrlPlayer = getClass().getResource("/images/player_south_straight.png");

    public Player(int x, int y, Image image) {
        _x = x;
        _y = y;
        _image = image;
        _state = new SouthState();
        loadImages();
    }

    public void move(int dx, int dy) {
//        _state.move(this, dx, dy);
        _state.move(this, dx, dy);
        _x += dx;
        _y += dy;

    }

    public void setState(PlayerState state) {
        _state = state;
    }

    public PlayerState getState() {
        return _state;
    }

    public byte interact(int x, int y) {
        if(x >= 140 && x <= 180 && y >= 90 && y <= 120) {
            System.out.println("interact: Television");
            return 0b00000100;
        } else if(x >= 70 && x <= 90 && y >= 90 && y <= 120) {
            System.out.println("interact: Fridge");
            return 0b00000010;
        } else if(x >= 475 && x <= 495 && y >= 90 && y <= 120) {
            System.out.println("interact: Clock");
            return 0b00000011;
        } else if(x >= 0 && x <= 20 && y >= 90 && y <= 120) {
            System.out.println("interact: Thermostat");
            return 0b00000001;
        }
        return 0b00000000;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(_image, _x, _y, null);
    }

    public void setX(int x) {
        _x = x;
    }

    public int getX() {
        return _x;
    }

    public void setY(int y) {
        _y = y;
    }

    public int getY() {
        return _y;
    }

    public void setImage(Image image) {
        _image = image;
    }

    private void loadImages() {
        URL imageUrlPlayer = getClass().getResource("/images/player_east_stand.png");
        if(imageUrlPlayer != null) {
            _eastStand = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_east_walk.png");
        if(imageUrlPlayer != null) {
            _eastWalk = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_north_straight.png");
        if(imageUrlPlayer != null) {
            _northStand = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_north_left.png");
        if(imageUrlPlayer != null) {
            _northLeft = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_north_right.png");
        if(imageUrlPlayer != null) {
            _northRight = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_south_left.png");
        if(imageUrlPlayer != null) {
            _southLeft = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_south_right.png");
        if(imageUrlPlayer != null) {
            _southRight = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_south_straight.png");
        if(imageUrlPlayer != null) {
            _southStand = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_west_stand.png");
        if(imageUrlPlayer != null) {
            _westStand = new ImageIcon(imageUrlPlayer).getImage();
        }

        imageUrlPlayer = getClass().getResource("/images/player_west_walk.png");
        if(imageUrlPlayer != null) {
            _westWalk = new ImageIcon(imageUrlPlayer).getImage();
        }
    }

    public Image getEastWalk() {
        return _eastWalk;
    }

    public Image getEastStand() {
        return _eastStand;
    }

    public Image getWestStand() {
        return _westStand;
    }

    public Image getWestWalk() {
        return _westWalk;
    }

    public Image getNorthStand() {
        return _northStand;
    }

    public Image getNorthLeft() {
        return _northLeft;
    }

    public Image getNorthRight() {
        return _northRight;
    }

    public Image getSouthStand() {
        return _southStand;
    }

    public Image getSouthLeft() {
        return _southLeft;
    }

    public Image getSouthRight() {
        return _southRight;
    }
}
