package com.luis.controller;

import com.luis.model.BallDTO;
import com.luis.model.GameModel;
import com.luis.view.GameView;

import java.util.ArrayList;

public class GameController {
    private GameView gameView;
    private GameModel gameModel;
    private boolean isRunning;

    public GameController() {
        this.gameModel = new GameModel(this);

        this.gameView = new GameView(this);

        this.isRunning = false;
        start();
    }

    public ArrayList<BallDTO> getBalls() {
        return gameModel.getBallDTOs();
    }

    public void start() {
        System.out.println("Game started");
        this.isRunning = true;

        gameModel.start();
    }

    public void pauseOrResume() {
        if (isRunning) {
            System.out.println("Game paused");
            this.isRunning = false;
        } else if (!isRunning) {
            System.out.println("Game resumed");
            this.isRunning = true;
        }

        gameModel.pauseOrResume();
    }

    public void stop() {
        System.out.println("Game stopped");
        this.isRunning = false;

        gameModel.stop();
    }
}

