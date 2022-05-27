package com.example.project.view;

import com.example.project.model.Music;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import com.example.project.App;
import com.example.project.controller.ProfileMenuController;
import com.example.project.model.Avatar;
import com.example.project.model.Database;
import com.example.project.model.User;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class ProfileMenuControllerView {
    ProfileMenuController profileMenuController = new ProfileMenuController(database);
    private static User user;
    private static Database database;


    @FXML
    private Button muteUnmute;
    @FXML
    public ImageView image1;
    @FXML
    public ImageView image2;
    @FXML
    public ImageView image3;
    @FXML
    public ImageView image4;
    @FXML
    private VBox imageSelectVBox;
    @FXML
    private javafx.scene.image.ImageView profileImage;
    @FXML
    private Label username;
    @FXML
    private javafx.scene.control.TextField userField;
    @FXML
    private javafx.scene.control.Label userLabel;
    @FXML
    private javafx.scene.control.Label passLabel;
    @FXML
    private javafx.scene.control.Button button1;
    @FXML
    private javafx.scene.control.Button button2;
    @FXML
    private PasswordField passField;
    @FXML
    private Pane pane;
    @FXML
    private HBox imageHBox1;
    @FXML
    private HBox imageHBox2;
    @FXML
    private HBox imageHBox3;
    @FXML
    private HBox imageHBox4;

    public static void setDatabase(Database database) {
        ProfileMenuControllerView.database = database;
    }

    public static void setUser(User user) {
        ProfileMenuControllerView.user = user;
    }

    public void initialize() throws MalformedURLException {
        setInitialMuteUnmute();
        image1.setImage(new Image(String.valueOf(Avatar.values()[0].getAvatarUrl())));
        image2.setImage(new Image(String.valueOf(Avatar.values()[1].getAvatarUrl())));
        image3.setImage(new Image(String.valueOf(Avatar.values()[2].getAvatarUrl())));
        image4.setImage(new Image(String.valueOf(Avatar.values()[3].getAvatarUrl())));
        imageSelectVBox.setVisible(false);
        profileImage.setImage(new Image(String.valueOf(user.getAvatarURL())));
        username.setText(user.getUsername());
        updateAvatar();
    }


    public void infoChange(MouseEvent mouseEvent) {
        passField.setVisible(true);
        userField.setVisible(true);
        button1.setVisible(true);
        button2.setVisible(true);
        passLabel.setVisible(true);
        userLabel.setVisible(true);
    }

    public void usernameChange(MouseEvent mouseEvent) {
        String output = profileMenuController.usernameChange(userField.getText(), user);
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
            alert.setContentText("username changed successfully!");
            alert.initOwner(App.getStage());
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initStyle(StageStyle.TRANSPARENT);
            setStyleInfo(alert);
            setStyleForButton(alert);
            alert.show();
        }
    }

    private void setStyleInfo(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setHeaderText(null);
        dialogPane.setGraphic(null);
        dialogPane.setStyle("-fx-background-radius: 20; -fx-background-color: #1c2c65; -fx-font-family: \"Matrix II Regular\";");
        dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-line-spacing: 5px;");
        dialogPane.getScene().setFill(javafx.scene.paint.Color.TRANSPARENT);
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

    public void passwordChange(MouseEvent mouseEvent) {
        user.setPassword(passField.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("password changed successfully!");
        alert.initOwner(App.getStage());
        alert.initModality(Modality.WINDOW_MODAL);
        alert.initStyle(StageStyle.TRANSPARENT);
        setStyleInfo(alert);
        setStyleForButton(alert);
        alert.show();
    }

    public void type(KeyEvent keyEvent) {
        if (passField.getText().length() < 8) {
            passField.setStyle("-fx-border-color: #ff0000;");
            button2.setDisable(true);
        } else {
            passField.setStyle("-fx-border-width: 0");
            button2.setDisable(false);
        }
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        App.changeFxml("/project/fxml/MainMenu.fxml", "login menu");
    }

    public void deleteAccount(MouseEvent mouseEvent) throws IOException {
        profileMenuController.deleteAccount(user);
        App.changeFxml("/project/fxml/LoginMenu.fxml", "login menu");
    }

    public void selectPicture(MouseEvent mouseEvent) {
        updateAvatar();
        imageSelectVBox.setVisible(true);
    }

    public void submitProfilePicture(MouseEvent mouseEvent) {
        imageSelectVBox.setVisible(false);
    }

    private void updateAvatar() {
        changeImage(imageHBox1, Avatar.AVATAR1.getAvatarUrl());
        changeImage(imageHBox2, Avatar.AVATAR2.getAvatarUrl());
        changeImage(imageHBox3, Avatar.AVATAR3.getAvatarUrl());
        changeImage(imageHBox4, Avatar.AVATAR4.getAvatarUrl());
    }

    private void changeImage(HBox imageHBox, URL url) {
        if (user.getAvatarURL().equals(url)) {
            imageHBox.setStyle("-fx-border-color: red;" +
                    "-fx-border-radius: 30 30 30 30;" +
                    "-fx-background-radius: 30 30 30 30;" +
                    "-fx-stroke-width: 20;");
            profileImage.setImage(new Image(String.valueOf(url)));
        } else imageHBox.setStyle("");
    }

    public void changeImage(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == image1) user.setAvatarURL(Avatar.AVATAR1.getAvatarUrl());
        if (mouseEvent.getSource() == image2) user.setAvatarURL(Avatar.AVATAR2.getAvatarUrl());
        if (mouseEvent.getSource() == image3) user.setAvatarURL(Avatar.AVATAR3.getAvatarUrl());
        if (mouseEvent.getSource() == image4) user.setAvatarURL(Avatar.AVATAR4.getAvatarUrl());
        updateAvatar();
    }

    public void chooseFromFile(MouseEvent mouseEvent) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG, PNG, JPEG Files", "*.jpg", "*.png", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(App.getStage());
        if (selectedFile != null) {
            ListView listView = new ListView();
            listView.getItems().add(selectedFile.getAbsoluteFile());
            String fileName = String.valueOf(listView.getItems());
            fileName = fileName.substring(1, fileName.length() - 1);
            user.setAvatarURL(Paths.get(fileName).toUri().toURL());
            this.profileImage.setImage(new Image(String.valueOf(Paths.get(fileName).toUri().toURL())));
            listView.getItems().clear();
        }
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