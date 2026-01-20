package com.snakesandladders.classes.objects;

public class Player {
    private String name;
    private int position;
    private Integer playerId;

    public Player(String name, Integer playerId) {
        this.name = name;
        this.playerId = playerId;
        this.position = 0;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.playerId;
    }
}
