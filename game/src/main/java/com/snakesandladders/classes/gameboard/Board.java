package com.snakesandladders.classes.gameboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private List<Space> spaces;
    private Map<Integer, Space> spacesByIndex;
    private int lastIndex;

    public Board(List<Space> spaces) {
        if (spaces == null || spaces.isEmpty()) {
            throw new IllegalArgumentException("Board must have at least one space.");
        }
        this.spaces = spaces;
        this.spacesByIndex = new HashMap<>();
        int maxIndex = 0;
        for (Space space : spaces) {
            if (space == null) {
                continue;
            }
            this.spacesByIndex.put(space.getIndex(), space);
            if (space.getIndex() > maxIndex) {
                maxIndex = space.getIndex();
            }
        }
        this.lastIndex = maxIndex;
    }

    public List<Space> getSpaces() {
        return this.spaces;
    }

    public Space getSpace(int index) {
        return this.spacesByIndex.get(index);
    }

    public int getLastIndex() {
        return this.lastIndex;
    }
}
