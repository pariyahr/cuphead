package com.example.project.view;

import com.example.project.model.Music;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import com.example.project.App;
import com.example.project.controller.LoginMenuController;
import com.example.project.model.Database;

import java.io.IOException;

public class LoginMenuControllerView {
    private static Database database;

    private LoginMenuController loginMenuController;

    @FXML
    public Button muteUnmute;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button guestButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    public void initialize() {
        loginMenuController = new LoginMenuController(database);
        setInitialMuteUnmute();
    }


    public static void setDatabase(Database database) {
        LoginMenuControllerView.database = database;
    }

    public void typePassword(javafx.scene.input.KeyEvent keyEvent) {
        if (password.getText().length() < 8) {
            password.setStyle("-fx-border-color: #ff0000;");
            registerButton.setDisable(true);
            loginButton.setDisable(true);
        } else {
            password.setStyle("-fx-border-width: 0");
            registerButton.setDisable(false);
            loginButton.setDisable(false);
        }
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        String output = loginMenuController.login(username.getText(), password.getText());
        if (output != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(output);
            alert.initOwner(App.getStage());
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initStyle(StageStyle.TRANSPARENT);
            setStyleError(alert);
            setStyleForButton(alert);
            alert.show();
        } else {
            App.changeFxml("/project/fxml/MainMenu.fxml", "main menu");
        }
    }

    public void register(MouseEvent mouseEvent) {
        String output = loginMenuController.register(username.getText(), password.getText());
        if (output != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(output);
            alert.initOwner(App.getStage());
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initStyle(StageStyle.TRANSPARENT);
            setStyleError(alert);
            setStyleForButton(alert);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("user added!");
            alert.initOwner(App.getStage());
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initStyle(StageStyle.TRANSPARENT);
            setStyleInfo(alert);
            setStyleForButton(alert);
            alert.show();
        }
    }

    public void startAsAGuest(MouseEvent mouseEvent) throws IOException {
        MainMenuControllerView.setUser(null);
        App.changeFxml("/project/fxml/MainMenu.fxml", "main menu");
    }

    private void setStyleInfo(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setHeaderText(null);
        dialogPane.setGraphic(null);
        dialogPane.setStyle("-fx-background-radius: 20; -fx-background-color: #1c2c65; -fx-font-family: \"Matrix II Regular\";");
        dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-line-spacing: 5px;");
        dialogPane.getScene().setFill(Color.TRANSPARENT);
    }

    private void setStyleError(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setHeaderText(null);
        dialogPane.setGraphic(null);
        dialogPane.setStyle("-fx-background-radius: 20; -fx-background-color: #af3232; -fx-font-family: \"Matrix II Regular\";");
        dialogPane.getScene().setFill(Color.TRANSPARENT);
        dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-line-spacing: 5px;");
    }

    private void setStyleForButton(Alert alert) {
        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 20; -fx-background-color: #564783; -fx-font-size: 16; -fx-text-fill: white;"));
        buttonBar.getButtons().forEach(b -> b.setCursor(Cursor.HAND));
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
