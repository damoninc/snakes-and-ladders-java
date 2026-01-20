package com.snakesandladders.classes.rules;

import com.snakesandladders.classes.gameboard.Board;
import com.snakesandladders.classes.gameboard.Space;
import com.snakesandladders.classes.objects.Player;
import com.snakesandladders.classes.state.GameState;

import java.util.List;

public class GameRulesEngine {
    private final Board board;
    private final boolean mustLandExactly;

    public GameRulesEngine(Board board, boolean mustLandExactly) {
        if (board == null) {
            throw new IllegalArgumentException("Board is required.");
        }
        this.board = board;
        this.mustLandExactly = mustLandExactly;
    }

    public void applyRoll(GameState state, int playerId, int roll) {
        if (state == null) {
            throw new IllegalArgumentException("Game state is required.");
        }
        if (state.isFinished()) {
            throw new IllegalStateException("Game is already finished.");
        }
        if (roll <= 0) {
            throw new IllegalArgumentException("Roll must be greater than zero.");
        }

        Integer currentPlayerId = state.getCurrentPlayerId();
        if (currentPlayerId == null) {
            throw new IllegalStateException("Game has not started.");
        }
        if (playerId != currentPlayerId) {
            throw new IllegalStateException("It is not player " + playerId + "'s turn.");
        }

        Player player = state.getPlayers().get(playerId);
        if (player == null) {
            throw new IllegalArgumentException("Unknown player: " + playerId);
        }

        state.setLastRoll(roll);
        int targetPosition = player.getPosition() + roll;
        if (targetPosition > board.getLastIndex()) {
            if (mustLandExactly) {
                advanceTurn(state);
                return;
            }
            targetPosition = board.getLastIndex();
        }

        player.setPosition(targetPosition);
        Space space = board.getSpace(targetPosition);
        if (space != null) {
            space.transportPlayer(player);
        }

        if (player.getPosition() >= board.getLastIndex()) {
            state.setWinnerId(playerId);
            state.setFinished(true);
            return;
        }

        advanceTurn(state);
    }

    private void advanceTurn(GameState state) {
        List<Integer> order = state.getTurnOrder();
        int currentIndex = order.indexOf(state.getCurrentPlayerId());
        if (currentIndex < 0) {
            throw new IllegalStateException("Current player is not in turn order.");
        }
        int nextIndex = (currentIndex + 1) % order.size();
        state.setCurrentPlayerId(order.get(nextIndex));
    }
}
