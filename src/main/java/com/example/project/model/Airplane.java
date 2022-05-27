package com.example.project.model;

import com.example.project.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Airplane extends ImageView {
    private static Airplane instance = null;
    private int timeLeftToShot = 5;
    private int bulletDamage;
    private int damage;

    private int health;

    public static Airplane getInstance() {
        if (instance == null)
            instance = new Airplane();
        return instance;
    }

    private Airplane() {
        this.setX(20);
        this.setY(300);
        this.setImage(new Image(String.valueOf(App.class.getResource("/project/images/images/blue.png"))));
        this.setFitWidth(70);
        this.setFitHeight(70);
    }

    public void moveLeft() {
        if (!(this.getX() < 0))
            this.setX(this.getX() - 10);
    }

    public void moveRight() {
        if (!(this.getX() + 70 > 1280))
            this.setX(this.getX() + 10);
    }

    public void moveUp() {
        if (!(this.getY() < 0))
            this.setY(this.getY() - 10);
    }

    public void moveDown() {
        if (!(this.getY() + 70 > 650))
            this.setY(this.getY() + 10);
    }

    public void shot(ArrayList<Bullet> bullets) {
        bullets.add(new Bullet((int) (this.getX() + 70), (int) this.getY()));
    }

    public int getTimeLeftToShot() {
        return timeLeftToShot;
    }

    public void setTimeLeftToShot(int timeLeftToShot) {
        this.timeLeftToShot = timeLeftToShot;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public boolean isHeated(ImageView object) {
        return object.getBoundsInParent().intersects(this.getBoundsInLocal());
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setNull(){
        instance = null;
    }
}