package com.luis.model;

import com.luis.controller.GameController;

import java.util.ArrayList;

public class GameModel {
    private GameController gameController;
    private BallDTO ballDTO;
    private Boolean hasGameStarted;


    public GameModel(GameController gameController) {
        this.gameController = gameController;
        this.hasGameStarted = false;
    }

    public void start() {
        if (!hasGameStarted) {
            hasGameStarted = true;
            ballDTO = new BallDTO(this);
        }
    }

    public void pauseOrResume() {
        if (hasGameStarted) {
            System.out.println("Game paused");
            this.hasGameStarted = false;
        } else if (!hasGameStarted) {
            System.out.println("Game resumed");
            this.hasGameStarted = true;
        }
        ballDTO.pauseOrResume();
    }

    public void stop() {
        hasGameStarted = false;
        ballDTO.clear();
    }

    public ArrayList<Ball> getBalls() {
        return ballDTO.getBallList();
    }
}
