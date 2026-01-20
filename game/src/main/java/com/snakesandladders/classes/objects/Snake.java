package com.snakesandladders.classes.objects;

public class Snake extends ATransportationMethod {

    public Snake(int startPosition, int endPosition) {
        super(startPosition, endPosition);

    }

    public void move(Player player) {
        player.setPosition(this.startPosition);
    }

}
