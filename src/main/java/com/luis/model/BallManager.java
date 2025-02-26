package com.luis.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BallManager {
    private static final int CANVAS_SIZE = 500;

    private int minDiameter = 25;
    private int maxDiameter = 50;
    private int minVel_X = 1;
    private int maxVel_X = 4;
    private int minVel_Y = 1;
    private int maxVel_Y = 4;

    private int quantity = 30;

    private ArrayList<Ball> balls;

    public BallManager() {
        this.balls = new ArrayList<>();
        initializeBalls();
    }

    private void initializeBalls() {
        Random random = new Random();
        for (int i = 1; i <= quantity; i++) {
            int diameter = random.nextInt(maxDiameter - minDiameter + 1) + minDiameter;
            int x = random.nextInt(CANVAS_SIZE - diameter);
            int y = random.nextInt(CANVAS_SIZE - diameter);
            //??
            int vel_X = random.nextInt(maxVel_X - minVel_X + 1) + minVel_X;
            int vel_Y = random.nextInt(maxVel_Y - minVel_Y + 1) + minVel_Y;

            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            Ball ball = new Ball(x, y, diameter, vel_X, vel_Y, CANVAS_SIZE, color);
            balls.add(ball);
        }
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void clear() {
        balls.clear();
    }

    public void pauseOrResumeBalls() {
        for (Ball ball : balls) {
            ball.setRunning(!ball.getRunning());
        }
    }
}