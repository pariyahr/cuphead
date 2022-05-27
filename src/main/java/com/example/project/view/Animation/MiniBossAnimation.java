package com.example.project.view.Animation;

import com.example.project.App;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import com.example.project.model.*;
import com.example.project.view.GameView;

import java.util.ArrayList;

public class MiniBossAnimation extends Transition {
    private MiniBoss miniBoss;
    private int speed = 6;
    public static ArrayList<Bullet> bullets;

    public MiniBossAnimation(MiniBoss miniBoss) {
        this.miniBoss = miniBoss;
        this.setCycleDuration(Duration.millis(30000));
    }
    @Override
    protected void interpolate(double frac) {
//        System.out.println(bullets.size());
        int realFrameNumber = (int) Math.floor(frac * 100) + 1;
        realFrameNumber %= 4;
        realFrameNumber++;
        miniBoss.setImage(new Image(String.valueOf(App.class.getResource("/project/images/MiniBossFly/purple/" + realFrameNumber + ".png"))));
        miniBoss.move(speed);
        if (Airplane.getInstance().isHeated(this.miniBoss)){
            Airplane.getInstance().setHealth(Airplane.getInstance().getHealth() - Airplane.getInstance().getDamage());
            GameView.getPane().getChildren().remove(miniBoss);
            pause();
        }
        if (miniBoss.getHealth() <= 0){
            GameView.scoreAmount += 10;
            GameView.getPane().getChildren().remove(miniBoss);
            pause();
        }
    }
}
