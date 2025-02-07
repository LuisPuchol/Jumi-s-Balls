package com.luis.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BallDTO {
    private GameModel gameModel;

    private static final int CANVAS_SIZE = 500;

    private int min_X = 0;
    private int min_y = 1;
    private int max_x = 0;
    private int max_y = 1;
    private Color color;
    private int minDiameter = 25;
    private int maxDiameter = 50;
    private int minVel_X = 1;
    private int maxVel_X = 4;
    private int minVel_Y = 1;
    private int maxVel_Y = 4;

    private int quantity = 30;

    private ArrayList<Ball> ballList;
    private Ball ball;

    public BallDTO(GameModel gameModel) {
        this.gameModel = gameModel;
        this.ballList = new ArrayList<>();

        Random random = new Random();
        for (int i = 1; i <= quantity; i++) {
            int diameter = random.nextInt(maxDiameter - minDiameter + 1) + minDiameter;
            int x = random.nextInt(CANVAS_SIZE - diameter);
            int y = random.nextInt(CANVAS_SIZE - diameter);
            int vel_X = random.nextInt(maxVel_X - minVel_X + 1) + minVel_X;
            int vel_Y = random.nextInt(maxVel_Y - minVel_Y + 1) + minVel_Y;

            color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));


            ball = new Ball(x, y, diameter, vel_X, vel_Y, CANVAS_SIZE, color);

            ballList.add(ball);
        }
    }

    public ArrayList<Ball> getBallList() {
        return ballList;
    }

    public void clear() {
        ballList.clear();
    }

    public void pauseOrResume() {
        for (Ball ball : ballList){
            if (!ball.getRunning()) {
                ball.setRunning(true);
            } else {
                ball.setRunning(false);
            }
        }
    }
}
