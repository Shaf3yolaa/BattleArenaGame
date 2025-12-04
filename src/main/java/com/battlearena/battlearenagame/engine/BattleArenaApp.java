package com.battlearena.battlearenagame.engine;

import com.battlearena.battlearenagame.models.*;
import com.battlearena.battlearenagame.physics.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BattleArenaApp extends Application {
    private Fighter player1;
    private Fighter player2;
    private List<Projectile> projectiles = new ArrayList<>();
    private Set<KeyCode> activeKeys = new HashSet<>();
    private PhysicsEngine physics;
    private boolean gameRunning = false;

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Battle Arena Game");
        VBox layout1 = new VBox(20);
        layout1.setStyle("-fx-padding: 20; -fx-alignment: center;");
        String[] fighters = {"Warrior", "Mage", "Archer"};

        Label p1 = new Label("Player 1:");
        ComboBox<String> p1Choice = new ComboBox<>();
        p1Choice.getItems().addAll(fighters);
        p1Choice.setValue("Select a Character");

        Label p2 = new Label("Player 2:");
        ComboBox<String> p2Choice = new ComboBox<>();
        p2Choice.getItems().addAll(fighters);
        p2Choice.setValue("Select a Character");

        Button startButton = new Button("LET'S FIGHT!");
        startButton.setOnAction(e -> startGame(p1Choice.getValue(), p2Choice.getValue()));

        layout1.getChildren().addAll(p1, p1Choice, p2, p2Choice, startButton);
        selectionScene = new Scene(layout1, 400, 400);
        window.setScene(selectionScene);
        window.show();
    }

    private Fighter createFighter(String type, double x, double y) {
        switch (type) {
            case "Mage":
                return new Mage(x, y);
            case "Archer":
                return new Archer(x, y);
            default:
                return new Warrior(x, y);
        }
    }

    private void startGame(String p1Type, String p2Type) {
        gameRoot = new Pane();
        gameRoot.setPrefSize(800, 600);
        physics = new PhysicsEngine(800, 600);

        player1 = createFighter(p1Type, 60, 300);
        player2 = createFighter(p2Type, 740, 300);

        player1.getView().setFill(Color.BabyBLUE);
        player2.getView().setFill(Color.red);

        Line separator = new Line(400, 0, 400, 600);

        p1HealthLabel = new Label("P1: " + player1.getHealth());
        p2HealthLabel = new Label("P2: " + player2.getHealth());

        p1HealthLabel.setLayoutX(20);
        p1HealthLabel.setLayoutY(20);

        p2HealthLabel.setLayoutX(680);
        p2HealthLabel.setLayoutY(20);

        gameRoot.getChildren().addAll(player1.getView(), player2.getView(), p1HealthLabel, p2HealthLabel, separator);

        gameScene = new Scene(gameRoot);

        gameScene.setOnKeyPressed(e -> activeKeys.add(e.getCode()));
        gameScene.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));

        window.setScene(gameScene);
        gameRunning = true;

        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameRunning) {
                    update(now);
                }
            }
        };
        loop.start();
    }

    private void update(long now) {
        long currentTime = System.currentTimeMillis();

        physics.movePlayer1(player1,
                activeKeys.contains(KeyCode.W),
                activeKeys.contains(KeyCode.S),
                activeKeys.contains(KeyCode.A),
                activeKeys.contains(KeyCode.D));

        physics.movePlayer2(player2,
                activeKeys.contains(KeyCode.UP),
                activeKeys.contains(KeyCode.DOWN),
                activeKeys.contains(KeyCode.LEFT),
                activeKeys.contains(KeyCode.RIGHT));

        if (activeKeys.contains(KeyCode.F) && player1.canShoot(currentTime)) {
            shoot(player1, true);
        }

        if (activeKeys.contains(KeyCode.L) && player2.canShoot(currentTime)) {
            shoot(player2, false);
        }

        List<Projectile> removed = new ArrayList<>();

        for (Projectile p : projectiles) {
            p.move();

            if (p.getX() < 0 || p.getX() > 800) {
                removed.add(p);
                gameRoot.getChildren().remove(p.getView());
                continue;
            }

            if (physics.checkCollision(p, player1) && p.active && p.isMovingRight() == false) {
                player1.takeDamage(p.getDamage());
                p.active = false;
                removed.add(p);
                gameRoot.getChildren().remove(p.getView());
            }

            if (physics.checkCollision(p, player2) && p.active && p.isMovingRight() == true) {
                player2.takeDamage(p.getDamage());
                p.active = false;
                removed.add(p);
                gameRoot.getChildren().remove(p.getView());
            }
        }

        projectiles.removeAll(removed);

        p1HealthLabel.setText("P1: " + player1.getHealth());
        p2HealthLabel.setText("P2: " + player2.getHealth());


        private void shoot (Fighter shooter,boolean right){
            double startX = right ? shooter.getX() + 45 : shooter.getX() - 10;
            double startY = shooter.getY() + 20;

            Projectile bullet = new Projectile(
                    startX,
                    startY,
                    shooter.getWeapon().getProjectileSpeed(),
                    shooter.getWeapon().getDamage(),
                    right
            );

            projectiles.add(bullet);
            gameRoot.getChildren().add(bullet.getView());
        }


    }
}