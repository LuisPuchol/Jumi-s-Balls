package com.luis.model;
import java.awt.*;

public class Ball implements Runnable {
    private int CANVAS_SIZE;

    private int x, y, vel_X, vel_Y, diameter;
    private Color color;
    private Boolean running;
    private Thread thread;

    public Ball(int x, int y, int diameter, int vel_X, int vel_Y, int CANVAS_SIZE, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.vel_X = vel_X;
        this.vel_Y = vel_Y;
        this.CANVAS_SIZE = CANVAS_SIZE;
        this.color = color;
        this.running = true;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (!running) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            move();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        if (x + vel_X < 0 || x + vel_X + diameter > CANVAS_SIZE) {
            vel_X *= -1;
        }
        if (y + vel_Y < 0 || y + vel_Y + diameter > CANVAS_SIZE) {
            vel_Y *= -1;
        }
        x += vel_X;
        y += vel_Y;
    }


    /***
     *
     * En las lineas 50 y 53 se hace el calculo para invertir el movimiento, no hace falta este metodo
     */
    public void rebound(int value) {
        switch (value) {
            case 0:
                vel_X = -(vel_X);
                vel_Y = -(vel_Y);
                break;
            case 1:
                vel_X = +(vel_X);
                vel_Y = +(vel_Y);
                break;
        }
        move();
    }

    public Boolean getRunning() {
        return running;
    }

    public synchronized void setRunning(Boolean running) {
        this.running = running;
        if (running) {
            notify();
        }
    }

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

    public int getVel_X() {
        return vel_X;
    }

    public void setVel_X(int vel_X) {
        this.vel_X = vel_X;
    }

    public int getVel_Y() {
        return vel_Y;
    }

    public void setVel_Y(int vel_Y) {
        this.vel_Y = vel_Y;
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