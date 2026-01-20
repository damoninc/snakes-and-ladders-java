package com.snakesandladders.classes.rules;

import com.snakesandladders.classes.gameboard.Board;
import com.snakesandladders.classes.gameboard.Space;
import com.snakesandladders.classes.objects.Die;
import com.snakesandladders.classes.objects.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GameEngine {
    private final Board gameBoard;
    private final Map<Integer, Player> players;
    private final Die die;
    private final boolean mustLandExactly;

    public GameEngine(Map<Integer, Player> players, Board gameBoard, Die die, boolean mustLandExactly) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Players are required to start the game.");
        }
        if (gameBoard == null) {
            throw new IllegalArgumentException("Game board is required to start the game.");
        }
        if (die == null) {
            throw new IllegalArgumentException("Die is required to start the game.");
        }
        this.players = players;
        this.gameBoard = gameBoard;
        this.die = die;
        this.mustLandExactly = mustLandExactly;
    }

    public GameEngine(Map<Integer, Player> players, Board gameBoard, Die die) {
        this(players, gameBoard, die, true);
    }

    public Player playGame() {
        List<Integer> turnOrder = new ArrayList<>(players.keySet());
        Collections.sort(turnOrder);
        System.out.println("Starting game with " + turnOrder.size() + " players. Target: " + gameBoard.getLastIndex());
        while (true) {
            for (Integer playerId : turnOrder) {
                Player player = players.get(playerId);
                if (player == null) {
                    continue;
                }
                System.out.println("Turn: " + player.getName() + " (position " + player.getPosition() + ")");
                int roll = die.rollDie();
                System.out.println("Rolled a " + roll);
                int targetPosition = player.getPosition() + roll;
                if (targetPosition > gameBoard.getLastIndex()) {
                    if (mustLandExactly) {
                        System.out.println("Overshot the end. Must land exactly, so stay at " + player.getPosition());
                        continue;
                    }
                    targetPosition = gameBoard.getLastIndex();
                }
                player.setPosition(targetPosition);
                System.out.println("Moved to " + player.getPosition());
                Space space = gameBoard.getSpace(targetPosition);
                if (space != null) {
                    int beforeTransport = player.getPosition();
                    space.transportPlayer(player);
                    int afterTransport = player.getPosition();
                    if (afterTransport != beforeTransport) {
                        System.out.println("Transported to " + afterTransport);
                    }
                }
                if (player.getPosition() >= gameBoard.getLastIndex()) {
                    System.out.println(player.getName() + " reached the end!");
                    return player;
                }
            }
        }
    }
}
