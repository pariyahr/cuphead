package com.example.project.view;

import com.example.project.model.Music;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import com.example.project.App;
import com.example.project.model.Game;
import com.example.project.model.User;

import java.io.IOException;

public class GameSettingsView {

    public static User user;
    @FXML
    private Button color;
    private int difficulty = 1;

    @FXML
    private Button gameMuteUnmute;
    @FXML
    private Button muteUnmute;
    private boolean isGameSoundMute = false;
    private boolean isBlachAndWhite = false;
    @FXML
    private Button easy;
    @FXML
    private Button medium;
    @FXML
    private Button hard;
    @FXML
    private Button devilMode;


    public void initialize() {
        setInitialMuteUnmute();
        medium.setStyle("-fx-border-color: #ff0000;");
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        App.changeFxml("/project/fxml/MainMenu.fxml", "main menu");
    }

    public void mute(MouseEvent mouseEvent) {
    }

    public void easy(MouseEvent mouseEvent) {
        easy.setStyle("-fx-border-color: #ff0000;");
        medium.setStyle("");
        hard.setStyle("");
        difficulty = 0;
    }

    public void hard(MouseEvent mouseEvent) {
        hard.setStyle("-fx-border-color: #ff0000;");
        medium.setStyle("");
        easy.setStyle("");
        difficulty = 2;
    }

    public void devil(MouseEvent mouseEvent) {
        devilMode.setStyle("-fx-border-color: #ff0000;");
        medium.setDisable(true);
        hard.setDisable(true);
        easy.setDisable(true);
        medium.setStyle("");
        easy.setStyle("");
        hard.setStyle("");
        difficulty = 3;
    }

    public void medium(MouseEvent mouseEvent) {
        medium.setStyle("-fx-border-color: #ff0000;");
        hard.setStyle("");
        easy.setStyle("");
        difficulty = 1;
    }


    public void start(MouseEvent mouseEvent) throws Exception {
        Music.getInstance().getMenuMusic().pause();
        if (!isGameSoundMute)
            Music.getInstance().getGameMusic().play();
        Game.getInstance().setGame(difficulty);
        GameView.user = user;
//        System.out.println(user);
        GameView.startGame();
    }

    public void muteUnMute(MouseEvent mouseEvent) {
        if (Music.getInstance().getMenuMusic().isMute()) {
            muteUnmute.setText("mute");
            Music.getInstance().getMenuMusic().setMute(false);
        } else {
            muteUnmute.setText("unmute");
            Music.getInstance().getMenuMusic().setMute(true);
        }
    }

    public void setInitialMuteUnmute() {
        if (Music.getInstance().getMenuMusic().isMute()) {
            muteUnmute.setText("unmute");
        } else {
            muteUnmute.setText("mute");
        }
    }


    public void muteUnMuteGameSound(MouseEvent mouseEvent) {
        if (isGameSoundMute) {
            isGameSoundMute = false;
            gameMuteUnmute.setText("mute");
        } else {
            isGameSoundMute = true;
            gameMuteUnmute.setText("unmute");
        }
    }

    public void color(MouseEvent mouseEvent) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.1);
        colorAdjust.setHue(-0.05);
        colorAdjust.setBrightness(0.01);
        colorAdjust.setSaturation(-1);
        App.getParent().setEffect(colorAdjust);
        App.isBlackAndWhite = true;
    }
}
