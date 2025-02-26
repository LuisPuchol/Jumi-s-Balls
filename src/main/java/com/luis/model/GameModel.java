package com.luis.model;

import com.luis.controller.GameController;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameModel {
    private GameController gameController;
    private BallManager ballManager;
    private Boolean hasGameStarted;

    public GameModel(GameController gameController) {
        this.gameController = gameController;
        this.hasGameStarted = false;
    }

    public void start() {
        if (!hasGameStarted) {
            hasGameStarted = true;
            ballManager = new BallManager();
        }
    }

    public void pauseOrResume() {
        if (hasGameStarted) {
            System.out.println("Game paused/resumed from model");
            ballManager.pauseOrResumeBalls();
        }
    }

    public void stop() {
        hasGameStarted = false;
        if (ballManager != null) {
            ballManager.clear();
        }
    }

    public ArrayList<Ball> getBalls() {
        return ballManager != null ? ballManager.getBalls() : new ArrayList<>();
    }

    // Método para convertir las bolas a DTOs para la vista
    public ArrayList<BallDTO> getBallDTOs() {
        if (ballManager == null) return new ArrayList<>();

        return ballManager.getBalls().stream()
                .map(BallDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}