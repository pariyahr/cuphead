package com.example.project.view.Animation;

import com.example.project.App;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import com.example.project.model.Boss;
import com.example.project.model.Egg;

public class BossShoot extends Transition {
    private Boss boss;
    private Egg egg;

    public BossShoot() {
        this.boss = Boss.getInstance();
        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double frac) {
        int realFrameNumber = (int) Math.floor(frac * 75) + 1;
        realFrameNumber %= 12;
        realFrameNumber++;
        boss.setImage(new Image(String.valueOf(App.class.getResource("/project/images/BossShoot/" + realFrameNumber + ".png"))));
        if (realFrameNumber == 5) {
            if (egg == null) {
                egg = new Egg((int) boss.getX(), (int) boss.getY());
                new BossShootAnimation(egg).play();
            }
        }
        if (realFrameNumber == 12) {
            new BossAnimation().play();
            pause();
        }
    }
}
