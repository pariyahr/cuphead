package com.example.project.model;

import com.example.project.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.project.view.GameView;

public class Egg extends ImageView {
    public Egg(int x, int y) {
        this.setImage(new Image(String.valueOf(App.class.getResource("/project/images/images/egg.png"))));
        this.setFitWidth(60);
        this.setFitHeight(60);
        this.setLayoutX(x);
        this.setLayoutY(y + 130);
        GameView.getPane().getChildren().add(this);
    }

    public void move(int speed) {
        this.setLayoutX(this.getLayoutX() - speed);
    }
}
