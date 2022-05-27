package com.example.project.model;

import javafx.scene.image.ImageView;
import com.example.project.view.Animation.BossShoot;

public class Boss extends ImageView {
    private static Boss instance = null;
    private int health = 1000;
    private boolean isGoingUpward = true;

    private int timeLeftToShot = 400;
    private int miniShot = 300;

    public static Boss getInstance() {
        if (instance == null)
            instance = new Boss();
        return instance;
    }

    private Boss() {
        this.setX(900);
        this.setY(300);
        this.setFitWidth(300);
        this.setFitHeight(300);
    }

    public void move(int speed) {
        if (!(this.getY() > 0))
            isGoingUpward = false;
        if (!(this.getY() + 300 < 680))
            isGoingUpward = true;
        if (isGoingUpward)
            this.setY(this.getY() - speed);
        else
            this.setY(this.getY() + speed);
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

    public int getTimeLeftToShot() {
        return timeLeftToShot;
    }

    public void setTimeLeftToShot(int timeLeftToShot) {
        this.timeLeftToShot = timeLeftToShot;
    }

    public void shot() {
        new BossShoot().play();
    }

    public int getMiniShot() {
        return miniShot;
    }

    public void setMiniShot(int miniShot) {
        this.miniShot = miniShot;
    }

    public void setNull(){
        instance = null;
    }
}
