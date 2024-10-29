package com.aigner.grasp.statePattern;

import com.aigner.grasp.gui.Player;

public interface PlayerState {
    void move(Player player, int x, int y);
}
