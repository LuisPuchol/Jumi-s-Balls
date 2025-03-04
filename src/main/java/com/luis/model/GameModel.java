package com.luis.model;

import com.luis.controller.GameController;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class GameModel {
    private static final int CANVAS_SIZE = 500;

    private int minDiameter = 25;
    private int maxDiameter = 50;
    private int minVel_X = 1;
    private int maxVel_X = 4;
    private int minVel_Y = 1;
    private int maxVel_Y = 4;

    private int quantity = 30;

    private GameController gameController;
    private ArrayList<Ball> balls;
    private Boolean hasGameStarted;

    public GameModel(GameController gameController) {
        this.gameController = gameController;
        this.hasGameStarted = false;
        this.balls = new ArrayList<>();
    }

    public void start() {
        if (!hasGameStarted) {
            hasGameStarted = true;
            initializeBalls();
        }
    }

    private void initializeBalls() {
        Random random = new Random();
        for (int i = 1; i <= quantity; i++) {
            int diameter = random.nextInt(maxDiameter - minDiameter + 1) + minDiameter;
            int x = random.nextInt(CANVAS_SIZE - diameter);
            int y = random.nextInt(CANVAS_SIZE - diameter);
            int vel_X = random.nextInt(maxVel_X - minVel_X + 1) + minVel_X;
            int vel_Y = random.nextInt(maxVel_Y - minVel_Y + 1) + minVel_Y;

            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            Ball ball = new Ball(x, y, diameter, vel_X, vel_Y, CANVAS_SIZE, color);
            balls.add(ball);
        }
    }

    public void pauseOrResume() {
        if (hasGameStarted) {
            System.out.println("Game paused/resumed from model");
            for (Ball ball : balls) {
                ball.setRunning(!ball.getRunning());
            }
        }
    }

    public void stop() {
        hasGameStarted = false;
        clear();
    }

    public void clear() {
        balls.clear();
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    // Método para convertir las bolas a DTOs para la vista
    public ArrayList<BallDTO> getBallDTOs() {
        ArrayList<BallDTO> ballDTOs = new ArrayList<>();

        if (!hasGameStarted) {
            return ballDTOs;
        }

        for (Ball ball : balls) {
            BallDTO dto = new BallDTO(ball);
            ballDTOs.add(dto);
        }

        return ballDTOs;
    }


    
    public ArrayList<BallDTO> getBallDTOsPro() {
        if (!hasGameStarted) return new ArrayList<>();

        return balls.stream()
                .map(BallDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}