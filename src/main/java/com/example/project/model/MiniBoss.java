package com.example.project.model;

import javafx.scene.image.ImageView;
import com.example.project.view.Animation.MiniBossAnimation;
import com.example.project.view.GameView;

public class MiniBoss extends ImageView {

    private int health = 2;

    public MiniBoss(int y) {
        this.setX(1400);
        this.setY(y);
        this.setFitWidth(90);
        this.setFitHeight(70);
        GameView.getPane().getChildren().add(this);
        new MiniBossAnimation(this).play();
    }

    public void move(int speed) {
        this.setLayoutX(this.getLayoutX() - speed);
    }

    public boolean isHeated(ImageView object) {
        return object.getBoundsInParent().intersects(this.getBoundsInLocal());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
