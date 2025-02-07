package com.luis;

import com.luis.controller.GameController;

public class Game {

    public Game() {
        System.out.println("Game created");
        new GameController();
    }

    public static void main(String[] args) {
        Game game = new Game();
    }

}