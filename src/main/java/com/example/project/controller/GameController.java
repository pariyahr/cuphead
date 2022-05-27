package com.example.project.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import com.example.project.model.Airplane;
import com.example.project.model.Bullet;
import com.example.project.model.Game;

import java.util.ArrayList;

public class GameController {

    private boolean isRightPressed = false;
    private boolean isLeftPressed = false;
    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private boolean isSpacePressed = false;


    public void moveAirplane(Airplane airplane) {
        airplane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String key = event.getCode().getName();
//                System.out.println(key);
                switch (key) {
                    case "Right":
                        isRightPressed = true;
                        break;
                    case "Left":
                        isLeftPressed = true;
                        break;
                    case "Up":
                        isUpPressed = true;
                        break;
                    case "Down":
                        isDownPressed = true;
                        break;
                    case "Space":
                        isSpacePressed = true;
                        break;
                }
            }
        });
        airplane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String key = event.getCode().getName();
//                System.out.println(key);
                switch (key) {
                    case "Right":
                        isRightPressed = false;
                        break;
                    case "Left":
                        isLeftPressed = false;
                        break;
                    case "Up":
                        isUpPressed = false;
                        break;
                    case "Down":
                        isDownPressed = false;
                        break;
                    case "Space":
                        isSpacePressed = false;
                        break;
                }
            }
        });
    }

    public void airplaneCommand(ArrayList<Bullet> bullets){
        if(isRightPressed)Airplane.getInstance().moveRight();
        if(isLeftPressed)Airplane.getInstance().moveLeft();
        if(isUpPressed)Airplane.getInstance().moveUp();
        if(isDownPressed)Airplane.getInstance().moveDown();
        if(isSpacePressed){
            if(Airplane.getInstance().getTimeLeftToShot() == 0){
                Airplane.getInstance().shot(bullets);
                Airplane.getInstance().setTimeLeftToShot(5);
            }
        }
    }

    public void setAirplaneHealth() {
        if (Game.getInstance().getDifficulty() == 0) Airplane.getInstance().setHealth(10);
        if (Game.getInstance().getDifficulty() == 1) Airplane.getInstance().setHealth(5);
        if (Game.getInstance().getDifficulty() == 2) Airplane.getInstance().setHealth(2);
    }

    public void setBulletDamage(){
        if (Game.getInstance().getDifficulty() == 0) Airplane.getInstance().setBulletDamage(3);
        if (Game.getInstance().getDifficulty() == 1) Airplane.getInstance().setBulletDamage(2);
        if (Game.getInstance().getDifficulty() == 2) Airplane.getInstance().setBulletDamage(1);
    }

    public void setAirplaneDamage(){
        if (Game.getInstance().getDifficulty() == 2) Airplane.getInstance().setDamage(3);
        if (Game.getInstance().getDifficulty() == 1) Airplane.getInstance().setDamage(2);
        if (Game.getInstance().getDifficulty() == 0) Airplane.getInstance().setDamage(1);
    }


    public void moveBullets2(ArrayList<Bullet> bullets) {

    }
}