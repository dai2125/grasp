package com.aigner.grasp.gui;

import com.aigner.grasp.StatePattern.PlayerState;

public class NormalState implements PlayerState {
    @Override
    public void move(Player player, int x, int y) {
        player.setX(player.getX() + x);
        player.setY(player.getY() + y);
//        System.out.println("Normalstate");
    }
}
