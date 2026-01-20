package com.snakesandladders.classes.objects;

public class Snake extends ATransportationMethod {

    public Snake(int startPosition, int endPosition) {
        super(startPosition, endPosition);
        if (startPosition <= endPosition) {
            throw new IllegalArgumentException("Snake start must be higher than end.");
        }
    }

    public void move(Player player) {
        player.setPosition(this.endPosition);
    }

}
