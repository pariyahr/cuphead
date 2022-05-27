package com.example.project.view.Animation;

import com.example.project.App;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import com.example.project.model.Boss;
import com.example.project.view.GameView;

public class DyingBoss extends Transition {
    private Boss boss;

    public DyingBoss() {
        this.boss = Boss.getInstance();
        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double frac) {
        int realFrameNumber = (int) Math.floor(frac * 100) + 1;
        realFrameNumber %= 44;
        realFrameNumber++;
        if (realFrameNumber == 44){
            GameView.getPane().getChildren().remove(boss);
            pause();
        }
//        if (realFrameNumber > 12)
//            boss.setFitHeight(boss.getImage().getHeight() + 1);
//        if (realFrameNumber > 26)
//            boss.setFitHeight(300);
        boss.setImage(new Image(String.valueOf(App.class.getResource("/project/images/Death/" + realFrameNumber + ".png"))));
    }
}
