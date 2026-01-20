package com.snakesandladders.interfaces;

import com.snakesandladders.classes.objects.Player;

public interface ITransportable {
    public void move(Player player);

    public int getStartPosition();

    public int getEndPosition();
}
