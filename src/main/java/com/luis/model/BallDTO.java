package com.luis.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BallDTO {
    private int x;
    private int y;
    private int diameter;
    private Color color;

    public BallDTO(Ball ball) {
        this.x = ball.getX();
        this.y = ball.getY();
        this.diameter = ball.getDiameter();
        this.color = ball.getColor();
    }

    // Getters y setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
