package com.aigner.grasp.gui;

import com.aigner.grasp.statePattern.PlayerState;

public class SouthState implements PlayerState {

    private int movement = 0;

    @Override
    public void move(Player player, int x, int y) {
        if(movement % 2 == 0) {
            player.setImage(player.getSouthLeft());
        } else {
            player.setImage(player.getSouthRight());
        }
//        player.setY(player.getY() + y);
        movement++;
//        System.out.println(movement);
    }
}
