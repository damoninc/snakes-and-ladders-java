package com.snakesandladders.classes.rules;

import com.snakesandladders.classes.gameboard.Board;
import com.snakesandladders.classes.gameboard.GameBoardConfigurator;
import com.snakesandladders.classes.objects.Die;
import com.snakesandladders.classes.objects.Player;
import com.snakesandladders.classes.state.GameState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameLifecycle {
    private static final int DEFAULT_PLAYER_COUNT = 2;
    private static final int MIN_PLAYER_COUNT = 2;
    private static final int DEFAULT_DIE_SIDES = 6;

    private final Board board;
    private final Die die;
    private final GameRulesEngine rulesEngine;
    private GameState state;

    public GameLifecycle() {
        this.board = new GameBoardConfigurator().createStandardBoard();
        try {
            this.die = new Die(DEFAULT_DIE_SIDES);
        } catch (Die.InvalidSidesException exception) {
            throw new IllegalStateException("Invalid die configuration.", exception);
        }
        this.rulesEngine = new GameRulesEngine(board, true);
        this.state = createNewState(DEFAULT_PLAYER_COUNT);
    }

    public synchronized GameState getState() {
        return state;
    }

    public synchronized GameState startNewGame(int playerCount) {
        this.state = createNewState(playerCount);
        return state;
    }

    public synchronized GameState rollForPlayer(int playerId) {
        int roll = die.rollDie();
        rulesEngine.applyRoll(state, playerId, roll);
        return state;
    }

    private GameState createNewState(int playerCount) {
        if (playerCount < MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException("At least " + MIN_PLAYER_COUNT + " players are required.");
        }
        Map<Integer, Player> players = new LinkedHashMap<>();
        for (int i = 1; i <= playerCount; i++) {
            players.put(i, new Player("Player " + i, i));
        }
        List<Integer> turnOrder = new ArrayList<>(players.keySet());
        GameState newState = new GameState(players, turnOrder, board.getLastIndex());
        newState.setCurrentPlayerId(turnOrder.get(0));
        return newState;
    }
}
