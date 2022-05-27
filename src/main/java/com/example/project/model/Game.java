package com.example.project.model;

public class Game {
    private static Game instance;

    private Game() {
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    private int difficulty;

    public void setGame(int difficulty) {
        setDifficulty(difficulty);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
