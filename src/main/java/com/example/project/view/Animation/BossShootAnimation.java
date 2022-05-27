package com.example.project.view.Animation;

import javafx.animation.Transition;
import javafx.util.Duration;
import com.example.project.model.Airplane;
import com.example.project.model.Boss;
import com.example.project.model.Egg;
import com.example.project.view.GameView;

public class BossShootAnimation extends Transition {
    private Boss boss;
    private int speed = 12;
    private Egg egg;

    public BossShootAnimation(Egg egg) {
        this.egg = egg;
        this.boss = Boss.getInstance();
        this.setCycleDuration(Duration.millis(9000));
    }

    @Override
    protected void interpolate(double frac) {
        egg.move(speed);
        if (Airplane.getInstance().isHeated(this.egg)){
            Airplane.getInstance().setHealth(Airplane.getInstance().getHealth() - Airplane.getInstance().getDamage());
            GameView.getPane().getChildren().remove(egg);
            pause();
        }
    }
}
