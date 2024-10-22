package com.aigner.grasp.StatePattern;

import com.aigner.grasp.gui.Player;

public class HungryState implements PlayerState {
    @Override
    public void move(Player player, int x, int y) {
        player.setX(player.getX() + x / 2);
        player.setY(player.getY() + y / 2);
//        System.out.println("HungryState");
    }
}
