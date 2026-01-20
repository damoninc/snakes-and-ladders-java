package com.snakesandladders.classes.objects;

public class Ladder extends ATransportationMethod {

    public Ladder(int startPosition, int endPosition) {
        super(startPosition, endPosition);
        if (startPosition >= endPosition) {
            throw new IllegalArgumentException("Ladder start must be lower than end.");
        }
    }

    public void move(Player player) {
        player.setPosition(this.endPosition);
    }

}
