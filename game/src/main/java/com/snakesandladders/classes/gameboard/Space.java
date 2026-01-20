package com.snakesandladders.classes.gameboard;

import com.snakesandladders.classes.objects.Player;
import com.snakesandladders.interfaces.ITransportable;

/**
 * Representation of the space on the gameboard of Snakes and Ladders
 * 
 */
public class Space {
    private ITransportable transportable;
    private int index;

    public Space(ITransportable transportable, int index) {
        this.transportable = transportable;
        this.index = index;
    }

    public Space(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void transportPlayer(Player player) {
        if (player == null || this.transportable == null) {
            return;
        }
        this.transportable.move(player);
    }
}
