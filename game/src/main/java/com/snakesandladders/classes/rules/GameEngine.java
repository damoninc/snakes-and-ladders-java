package com.snakesandladders.classes.rules;

import com.snakesandladders.classes.objects.Player;
import com.snakesandladders.classes.gameboard.Board;
import java.util.Map;

public class GameEngine {
    private Board gameBoard;
    private Map<Integer, Player> players;

    public GameEngine(Map<Integer, Player> players, Board gameBoard) {
        this.players = players;
        this.gameBoard = gameBoard;
    }

}
