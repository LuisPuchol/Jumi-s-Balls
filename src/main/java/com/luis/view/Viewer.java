package com.luis.view;

import com.luis.model.BallDTO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Viewer extends Canvas {
    private ArrayList<BallDTO> ballDTOs;
    private BufferedImage offscreenBuffer;
    private Graphics2D offscreenGraphics;

    public Viewer() {
        System.out.println("Viewers creado");
        this.setSize(500, 500);
        this.setBackground(Color.CYAN);
        this.ballDTOs = new ArrayList<>();

        offscreenBuffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        offscreenGraphics = offscreenBuffer.createGraphics();
    }

    public void drawBall(Graphics2D g, int x, int y, int diameter, Color color) {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public void render(ArrayList<BallDTO> ballDTOs) {
        this.ballDTOs = ballDTOs;
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        if (ballDTOs == null) return;

        offscreenGraphics.setColor(Color.CYAN);
        offscreenGraphics.fillRect(0, 0, 500, 500);

        for (BallDTO ballDTO : ballDTOs) {
            drawBall(offscreenGraphics, ballDTO.getX(), ballDTO.getY(),
                    ballDTO.getDiameter(), ballDTO.getColor());
        }

        g.drawImage(offscreenBuffer, 0, 0, this);
    }

    public Canvas getCanvas() {
        return this;
    }
}