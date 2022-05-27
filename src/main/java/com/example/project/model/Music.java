package com.example.project.model;

import com.example.project.App;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class Music {
    private static Music instance;

    public static Music getInstance() {
        if (instance == null)
            instance = new Music();
        return instance;
    }

    private final MediaPlayer menuMusic =
            new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/project/music/Menu2.mp3")).toString()));
    private final MediaPlayer gameMusic =
            new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/project/music/Game.mp3")).toString()));

    public MediaPlayer getMenuMusic() {
        return menuMusic;
    }

    public MediaPlayer getGameMusic() {
        return gameMusic;
    }
}