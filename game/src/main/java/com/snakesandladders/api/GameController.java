package com.snakesandladders.api;

import com.snakesandladders.classes.rules.GameLifecycle;
import com.snakesandladders.classes.state.GameState;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameLifecycle lifecycle;

    public GameController(GameLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @GetMapping("/state")
    public GameState getState() {
        return lifecycle.getState();
    }

    @PostMapping("/start")
    public GameState startNewGame(@RequestParam(name = "players", defaultValue = "2") int players) {
        try {
            return lifecycle.startNewGame(players);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    @PostMapping("/roll/{playerId}")
    public GameState rollForPlayer(@PathVariable("playerId") int playerId) {
        try {
            return lifecycle.rollForPlayer(playerId);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }
}
