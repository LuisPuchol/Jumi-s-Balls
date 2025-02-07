package com.luis.view;

import com.luis.model.Ball;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Viewer extends Canvas {
    private ArrayList<Ball> balls;
    private GameView gameView;

    public Viewer() {
        System.out.println("Viewers creado");
        this.setSize(500, 500);
        this.setBackground(Color.CYAN);
        this.balls = new ArrayList<>();
    }

    public void drawBall(Graphics2D g, int x, int y, int diameter, Color color) {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public void render(ArrayList<Ball> balls) {
        this.balls = balls;
        repaint();

    }

    public void paint(Graphics g) {
        if (balls == null) return;

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.CYAN);
        g2D.fillRect(0, 0, getWidth(), getHeight());

        for (Ball ball : balls) {
            drawBall(g2D, ball.getX(), ball.getY(), ball.getDiameter(), ball.getColor());
        }
    }

    public Canvas getCanvas() {
        return this;
    }

}
