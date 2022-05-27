package com.example.project.view;

import com.example.project.model.Music;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import com.example.project.App;
import com.example.project.model.Database;
import com.example.project.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class ScoreBoardView {
    public static User user;
    public static Database database;

    @FXML
    private Pane pane;
    @FXML
    private Button muteUnmute;


    public void initialize() {
        setInitialMuteUnmute();
        VBox vBox1 = new VBox();
        vBox1.prefWidth(200);
        vBox1.prefHeight(400);

        Label label = new Label("username");
        label.setPrefWidth(200);
        label.setPrefHeight(20);
        label.setAlignment(Pos.CENTER);
        vBox1.getChildren().add(label);
        ArrayList<User> users = database.getUsers();
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(i).getScore() < users.get(j).getScore()) {
                    User temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
            }
        }
        for (int i = 0; i < Math.min(10, users.size()); i++) {
            label = new Label(users.get(i).getUsername());
            label.setPrefWidth(200);
            label.setPrefHeight(20);
            label.setAlignment(Pos.CENTER);
            setStyleForFirst3(i, label);
            vBox1.getChildren().add(label);
        }

        VBox vBox2 = new VBox();
        vBox2.prefWidth(200);
        vBox2.prefHeight(400);
        label = new Label("score:");
        label.setPrefWidth(200);
        label.setPrefHeight(20);
        label.setAlignment(Pos.CENTER);
        vBox2.getChildren().add(label);
        for (int i = 0; i < Math.min(10, users.size()); i++) {
            label = new Label(Integer.toString(users.get(i).getScore()));
            label.setPrefWidth(200);
            label.setPrefHeight(20);
            label.setAlignment(Pos.CENTER);
            setStyleForFirst3(i, label);
            vBox2.getChildren().add(label);
        }

        HBox hBox = new HBox();
        hBox.setLayoutX(440);
        hBox.setLayoutY(135);
        hBox.setSpacing(20);
        hBox.prefWidth(400);
        hBox.prefHeight(400);
        hBox.getChildren().addAll(vBox1, vBox2);
        pane.getChildren().add(hBox);
    }

    private void setStyleForFirst3(int rank, Label label) {
        if (rank == 0) label.setStyle("-fx-background-color: gold");
        if (rank == 1) label.setStyle("-fx-background-color: silver");
        if (rank == 2) label.setStyle("-fx-background-color: #ab5711");
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        App.changeFxml("/project/fxml/MainMenu.fxml", "Score Board");
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
}