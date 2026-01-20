package com.snakesandladders.classes.gameboard;

import java.util.List;

public class Board {
    private List<Space> spaces;

    public Board(List<Space> spaces) {
        this.spaces = spaces;
    }

    public List<Space> getSpaces() {
        return this.spaces;
    }

}
