package com.example.project.view.Animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import com.example.project.model.Airplane;
import com.example.project.model.Boss;
import com.example.project.model.Bullet;
import com.example.project.model.MiniBoss;
import com.example.project.view.GameView;

import java.util.ArrayList;

public class BulletAnimation extends Transition {
    private Bullet bullet;
    private int speed = 15;

    public static ArrayList<MiniBoss> miniBosses;

    public BulletAnimation(Bullet bullet) {
        this.bullet = bullet;
        this.setCycleDuration(Duration.millis(5000));
    }

    @Override
    protected void interpolate(double frac) {
        bullet.move(speed);
        if (Boss.getInstance().isHeated(this.bullet)) {
            GameView.scoreAmount += 2;
            Boss.getInstance().setHealth(Boss.getInstance().getHealth()
                    - Airplane.getInstance().getBulletDamage());
            GameView.getPane().getChildren().remove(bullet);
            pause();
        }
        for (int i = 0; i < miniBosses.size(); i++) {
            if (miniBosses.get(i).isHeated(this.bullet)) {
                miniBosses.get(i).setHealth(miniBosses.get(i).getHealth() - 1);
                GameView.getPane().getChildren().remove(bullet);
                pause();
            }
        }
    }
}