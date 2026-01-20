package com.snakesandladders.classes.state;

import com.snakesandladders.classes.objects.Player;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GameState {
    private final Map<Integer, Player> players;
    private final List<Integer> turnOrder;
    private final int boardSize;
    private Integer currentPlayerId;
    private Integer winnerId;
    private Integer lastRoll;
    private boolean finished;

    public GameState(Map<Integer, Player> players, List<Integer> turnOrder, int boardSize) {
        this.players = Collections.unmodifiableMap(players);
        this.turnOrder = Collections.unmodifiableList(turnOrder);
        this.boardSize = boardSize;
    }

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public List<Integer> getTurnOrder() {
        return turnOrder;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Integer getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(Integer currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
    }

    public String getWinnerName() {
        if (winnerId == null) {
            return null;
        }
        Player winner = players.get(winnerId);
        return winner == null ? null : winner.getName();
    }

    public Integer getLastRoll() {
        return lastRoll;
    }

    public void setLastRoll(Integer lastRoll) {
        this.lastRoll = lastRoll;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
