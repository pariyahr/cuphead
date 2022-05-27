package com.example.project.model;

import com.example.project.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.project.view.Animation.BulletAnimation;
import com.example.project.view.GameView;

public class Bullet extends ImageView {
    public Bullet(int x, int y) {
        this.setImage(new Image(String.valueOf(App.class.getResource("/project/images/images/bullet.png"))));
        this.setFitWidth(30);
        this.setFitHeight(15);
        this.setLayoutX(x);
        this.setLayoutY(y + 35);
        GameView.getPane().getChildren().add(this);
        new BulletAnimation(this).play();
    }

    public void move(int speed) {
        this.setLayoutX(this.getLayoutX() + speed);
    }
}
