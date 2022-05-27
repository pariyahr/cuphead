package com.example.project.view.Animation;

import com.example.project.App;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import com.example.project.model.Boss;
import com.example.project.view.GameView;

public class BossAnimation extends Transition {
    private Boss boss;
    private int speed = 3;
    public BossAnimation() {
        this.boss = Boss.getInstance();
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        boss.move(speed);
        int realFrameNumber = (int) Math.floor(frac * 6.25) + 1;
        realFrameNumber %= 6;
        realFrameNumber++;
        boss.setImage(new Image(String.valueOf(App.class.getResource("/project/images/BossFly/" + realFrameNumber + ".png"))));
        if (boss.getHealth() <= 0){
            GameView.scoreAmount += 50;
            new DyingBoss().play();
            pause();
        }
    }
}
