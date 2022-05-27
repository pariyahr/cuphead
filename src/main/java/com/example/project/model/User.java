package com.example.project.model;

import java.net.URL;
import java.util.Random;

public class User {
    private String password;
    private String username;
    private int score = 0;
    private int scoreTime = 5;
    private URL avatarURL;

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        Random random = new Random();
        this.avatarURL = Avatar.values()[Math.abs(random.nextInt() % 4)].getAvatarUrl();
        this.score = 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public URL getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(URL avatarURL) {
        this.avatarURL = avatarURL;
    }

    public int getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(int scoreTime) {
        this.scoreTime = scoreTime;
    }
}
