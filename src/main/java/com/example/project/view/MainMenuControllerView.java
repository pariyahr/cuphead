package com.example.project.view;

import com.example.project.model.Music;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import com.example.project.App;
import com.example.project.model.Database;
import com.example.project.model.User;

import java.io.IOException;

public class MainMenuControllerView {
    private static User user;
    private static Database database;

    @FXML
    private Button muteUnmute;
    @FXML
    private Button profileMenu;

    public static void setDatabase(Database database) {
        MainMenuControllerView.database = database;
    }

    public static void setUser(User user) {
        MainMenuControllerView.user = user;
    }


    public void initialize() {
        setInitialMuteUnmute();
        if (user == null) profileMenu.setDisable(true);
    }

    public void profile(MouseEvent mouseEvent) throws IOException {
        ProfileMenuControllerView.setUser(user);
        ProfileMenuControllerView.setDatabase(database);
        App.changeFxml("/project/fxml/ProfileMenu.fxml", "profile menu");
    }


    public void scoreBoard(MouseEvent mouseEvent) throws IOException {
        ScoreBoardView.user = user;
        ScoreBoardView.database = database;
        App.changeFxml("/project/fxml/ScoreBoard.fxml", "score board");
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        App.changeFxml("/project/fxml/LoginMenu.fxml", "login menu");
    }

    public void startGame(MouseEvent mouseEvent) throws IOException {
        GameSettingsView.user = user;
//        System.out.println(user);
        App.changeFxml("/project/fxml/GameSettings.fxml", "game settings");
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


    public void setInitialMuteUnmute(){
        if (Music.getInstance().getMenuMusic().isMute()) {
            muteUnmute.setText("unmute");
        } else {
            muteUnmute.setText("mute");
        }
    }
}