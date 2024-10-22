package com.aigner.grasp.StatePattern;

import com.aigner.grasp.gui.Player;

public interface PlayerState {
    void move(Player player, int x, int y);
}
