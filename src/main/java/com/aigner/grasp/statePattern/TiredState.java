package com.aigner.grasp.statePattern;

import com.aigner.grasp.gui.Player;

public class TiredState implements PlayerState {
    @Override
    public void move(Player player, int x, int y) {
        player.setX(player.getX() + x * 5);
        player.setY(player.getY() + y * 5);
//        System.out.println("TiredState");
    }
}
