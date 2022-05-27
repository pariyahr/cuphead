package com.example.project;

import com.example.project.model.Music;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;
import com.example.project.model.Database;
import com.example.project.view.LoginMenuControllerView;
import com.example.project.view.MainMenuControllerView;
import com.example.project.view.ProfileMenuControllerView;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    public static boolean isBlackAndWhite = false;
    private static Stage stage;
    private static Parent parent;
    private static Scene scene;

    public static void main(String[] args) {
        Music.getInstance().getGameMusic().setCycleCount(-1);
        Music.getInstance().getMenuMusic().setCycleCount(-1);
        Database database = new Database();
        LoginMenuControllerView.setDatabase(database);
        MainMenuControllerView.setDatabase(database);
        ProfileMenuControllerView.setDatabase(database);
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Parent getParent() {
        return parent;
    }

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Music.getInstance().getMenuMusic().play();
        URL address_login_page = new URL(App.class.getResource("/project/fxml/LoginMenu.fxml").toString());
        Parent root = FXMLLoader.load(address_login_page);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("login menu");
        stage.setResizable(false);
        App.stage = stage;
        App.parent = root;
        App.scene = scene;
        stage.show();
    }

    public static void setRoot(Parent root) throws IOException {
        App.scene.setRoot(root);
        if (isBlackAndWhite) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setContrast(0.1);
            colorAdjust.setHue(-0.05);
            colorAdjust.setBrightness(0.01);
            colorAdjust.setSaturation(-1);
            root.setEffect(colorAdjust);
        }
    }

    public static void changeFxml(String path, String title) throws IOException {
        URL address = new URL(App.class.getResource(path).toString());
        Parent root = FXMLLoader.load(address);
        App.parent = root;
        setRoot(root);
        stage.setTitle(title);
    }

}