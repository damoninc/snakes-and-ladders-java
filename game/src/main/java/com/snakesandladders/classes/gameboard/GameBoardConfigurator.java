package com.snakesandladders.classes.gameboard;

import com.snakesandladders.classes.objects.Ladder;
import com.snakesandladders.classes.objects.Snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardConfigurator {
    private static final int BOARD_SIZE = 100;

    public Board createStandardBoard() {
        Map<Integer, Space> spacesByIndex = new HashMap<>();
        for (int index = 1; index <= BOARD_SIZE; index++) {
            spacesByIndex.put(index, new Space(index));
        }

        addLadder(spacesByIndex, 2, 38);
        addLadder(spacesByIndex, 7, 14);
        addLadder(spacesByIndex, 8, 31);
        addLadder(spacesByIndex, 15, 26);
        addLadder(spacesByIndex, 28, 84);
        addLadder(spacesByIndex, 21, 42);
        addLadder(spacesByIndex, 36, 44);
        addLadder(spacesByIndex, 51, 67);
        addLadder(spacesByIndex, 71, 91);
        addLadder(spacesByIndex, 78, 98);
        addLadder(spacesByIndex, 87, 94);

        addSnake(spacesByIndex, 16, 6);
        addSnake(spacesByIndex, 46, 25);
        addSnake(spacesByIndex, 49, 11);
        addSnake(spacesByIndex, 62, 19);
        addSnake(spacesByIndex, 64, 60);
        addSnake(spacesByIndex, 74, 53);
        addSnake(spacesByIndex, 89, 68);
        addSnake(spacesByIndex, 92, 88);
        addSnake(spacesByIndex, 95, 75);
        addSnake(spacesByIndex, 99, 80);

        List<Space> spaces = new ArrayList<>(BOARD_SIZE);
        for (int index = 1; index <= BOARD_SIZE; index++) {
            spaces.add(spacesByIndex.get(index));
        }
        return new Board(spaces);
    }

    private void addLadder(Map<Integer, Space> spacesByIndex, int start, int end) {
        spacesByIndex.put(start, new Space(new Ladder(start, end), start));
    }

    private void addSnake(Map<Integer, Space> spacesByIndex, int start, int end) {
        spacesByIndex.put(start, new Space(new Snake(start, end), start));
    }
}
