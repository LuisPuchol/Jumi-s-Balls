package com.luis.controller;

import com.luis.model.Ball;
import com.luis.model.GameModel;
import com.luis.view.GameView;

import java.util.ArrayList;

public class GameController {
    private GameView gameView;
    private GameModel gameModel;
    private boolean isRunning;

    public GameController() {
        this.gameView = new GameView(this);
        this.gameModel = new GameModel(this);

        this.isRunning = false;
        start();
    }

    public ArrayList<Ball> getBalls() {
        return gameModel.getBalls();
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
