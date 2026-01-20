package com.snakesandladders.classes.objects;

public class Ladder extends ATransportationMethod {

    public Ladder(int startPosition, int endPosition) {
        super(startPosition, endPosition);

    }

    public void move(Player player) {
        player.setPosition(this.endPosition);
    }

}
