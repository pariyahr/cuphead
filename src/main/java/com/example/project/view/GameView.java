package com.example.project.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import com.example.project.App;
import com.example.project.controller.GameController;
import com.example.project.model.*;
import com.example.project.view.Animation.BossAnimation;
import com.example.project.view.Animation.BulletAnimation;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class GameView {
    public Timeline timeline;
    public static Game game;
    public static User user;
    public static int scoreAmount = 0;
    public static GameController gameController = new GameController();
    private static Pane pane;
    private static ArrayList<Bullet> bullets;
    private static ArrayList<MiniBoss> miniBosses = new ArrayList<>();
    @FXML
    private Pane end;
    @FXML
    private Label state;
    @FXML
    private Label finalTime;
    @FXML
    private Label finalScore;

    @FXML
    private Label score;
    @FXML
    private Label clock;
    private long startGameTime;
    @FXML
    private Label bossHealth;

    @FXML
    private Label airplaneHP;

    public static void startGame() throws Exception {
        Pane pane = FXMLLoader.load(new URL(Objects.requireNonNull(
                App.class.getResource("/project/fxml/Game.fxml")).toString()));
        bullets = new ArrayList<>();
        Airplane airplane = Airplane.getInstance();
        Boss boss = Boss.getInstance();
        pane.getChildren().add(boss);
        new BossAnimation().play();
        gameController.setAirplaneHealth();
        gameController.setBulletDamage();
        gameController.setAirplaneDamage();
        moveBullets(bullets);
        pane.getChildren().add(airplane);
        GameView.pane = pane;
        App.setRoot(pane);
        airplane.requestFocus();
    }

    private static void moveBullets(ArrayList<Bullet> bullets) {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(30),
                        event -> {
                            if (Airplane.getInstance().getTimeLeftToShot() > 0)
                                Airplane.getInstance().setTimeLeftToShot(Airplane.getInstance().getTimeLeftToShot() - 1);
                            gameController.moveBullets2(bullets);
                        }
                )
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public Pane content;

    public static Pane getPane() {
        return pane;
    }

    public void initialize() {
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(content, xPosition.get()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(40), new KeyValue(xPosition, -15000))
        );
        timeline.setCycleCount(-1);
        timeline.play();
        updateEverything();
        startGameTime = System.currentTimeMillis();
    }

    void setBackgroundPositions(Pane content, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition / 6 + "px bottom," +
                "left " + xPosition / 5 + "px bottom," +
                "left " + xPosition / 4 + "px bottom," +
                "left " + xPosition / 3 + "px bottom," +
                "left " + xPosition / 2 + "px bottom," +
                "left " + xPosition + "px bottom;";
        content.setStyle(style);
    }

    private void updateEverything() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(30),
                        event -> {
                            for (MiniBoss miniBoss : miniBosses) {
                                if (miniBoss.getHealth() <= 0) {
                                    pane.getChildren().remove(miniBoss);
//                                    miniBosses.remove(miniBoss);
                                    break;
                                }
                            }
                            gameController.airplaneCommand(bullets);
                            gameController.moveAirplane(Airplane.getInstance());
                            if (Airplane.getInstance().getHealth() < 0) {
                                this.airplaneHP.setText("HP: 0");
                            } else
                                this.airplaneHP.setText("HP: " + Airplane.getInstance().getHealth());
                            if (Boss.getInstance().getHealth() < 0) {
                                this.bossHealth.setText("0%");
                            } else
                                this.bossHealth.setText(Boss.getInstance().getHealth() / 10 + "%");
                            if (Boss.getInstance().getTimeLeftToShot() != 0)
                                Boss.getInstance().setTimeLeftToShot(Boss.getInstance().getTimeLeftToShot() - 1);
                            else {
                                Boss.getInstance().setTimeLeftToShot(900);
                                Boss.getInstance().shot();
                            }
                            if (Boss.getInstance().getMiniShot() != 0)
                                Boss.getInstance().setMiniShot(Boss.getInstance().getMiniShot() - 1);
                            else
                                Boss.getInstance().setMiniShot(600);
                            if (Boss.getInstance().getMiniShot() == 1 || Boss.getInstance().getMiniShot() == 21 || Boss.getInstance().getMiniShot() == 41 || Boss.getInstance().getMiniShot() == 61) {
                                int y = Boss.getInstance().getMiniShot() * 10;
                                miniBosses.add(new MiniBoss(y));
                            }
                            if (miniBosses.size() > 4)
                                miniBosses.remove(miniBosses.get(0));
                            BulletAnimation.miniBosses = miniBosses;
//                            System.out.println(user);
                            this.score.setText("score: " + scoreAmount);
//                            if (Airplane.getInstance().isHeated(Boss.getInstance()))
//                                Airplane.getInstance().setHealth(Airplane.getInstance().getHealth() - Airplane.getInstance().getDamage());
                            setTime();
                            if (Boss.getInstance().getHealth() <= 0){
                                state.setText("victory!");
                                state.setTextFill(Paint.valueOf("green"));
                                end();
                            }
                            else if (Airplane.getInstance().getHealth() <= 0){
                                pane.getChildren().remove(Boss.getInstance());
                                Boss.getInstance().setNull();
                                state.setText("defeat!");
                                state.setTextFill(Paint.valueOf("red"));
                                end();
                            }
                        }
                )
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

    private void end(){
        end.setVisible(true);
        finalScore.setText("score: " + scoreAmount);
        DateFormat dateFormat = new SimpleDateFormat("mm:ss");
        long difference = System.currentTimeMillis() - startGameTime;
        difference -= 1800000;
        finalTime.setText("time: " + dateFormat.format(difference));
        pane.getChildren().remove(Airplane.getInstance());
        Airplane.getInstance().setNull();
        score.setVisible(false);
        airplaneHP.setVisible(false);
        clock.setVisible(false);
        bossHealth.setVisible(false);
        if (user != null){
            if (user.getScore() < scoreAmount)
                user.setScore(scoreAmount);
        }
        timeline.pause();
    }

    private void setTime(){
        DateFormat dateFormat = new SimpleDateFormat("mm:ss");
        long difference = System.currentTimeMillis() - startGameTime;
        difference -= 1800000;
        clock.setText(dateFormat.format(difference));
    }

    public void returnMain(MouseEvent mouseEvent) throws IOException {
        App.changeFxml("/project/fxml/MainMenu.fxml", "main menu");
    }

    public void replay(MouseEvent mouseEvent) throws Exception {
        Boss.getInstance().setNull();
        scoreAmount = 0;
        GameView.startGame();
    }
}