package com.aigner.grasp.statePattern;

import com.aigner.grasp.gui.Player;

public class WestState implements PlayerState {

    private int movement = 0;

    @Override
    public void move(Player player, int x, int y) {
        if(movement % 2 == 0) {
            player.setImage(player.getWestWalk());
        } else {
            player.setImage(player.getWestStand());
        }
//        player.setX(player.getX() - x);
        movement++;
//        System.out.println(movement);
    }
}
