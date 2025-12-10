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
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

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
    private Pane gameRoot;
    private Label p1HealthLabel;
    private Label p2HealthLabel;
    private Label p1WeaponLabel;
    private Label p2WeaponLabel;
    private Stage window;
    private Scene selectionScene;
    private Scene gameScene;

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
        player1.getView().setFill(Color.BLUE);
        player2 = createFighter(p2Type, 740, 300);
        player2.getView().setFill(Color.RED);

        Line separator = new Line(400, 0, 400, 600);

        p1HealthLabel = new Label("P1: " + player1.getHealth());
        p1HealthLabel.setLayoutX(20);
        p1HealthLabel.setLayoutY(20);

        p2HealthLabel = new Label("P2: " + player2.getHealth());
        p2HealthLabel.setLayoutX(680);
        p2HealthLabel.setLayoutY(20);

        p1WeaponLabel = new Label("Weapon: " + player1.getWeapon().getClass().getSimpleName());
        p1WeaponLabel.setLayoutX(20);
        p1WeaponLabel.setLayoutY(45);

        p2WeaponLabel = new Label("Weapon: " + player2.getWeapon().getClass().getSimpleName());
        p2WeaponLabel.setLayoutX(680);
        p2WeaponLabel.setLayoutY(45);

        gameRoot.getChildren().addAll(player1.getView(), player2.getView(), p1HealthLabel, p2HealthLabel, p1WeaponLabel, p2WeaponLabel, separator);
        gameScene = new Scene(gameRoot);
        gameScene.setOnKeyPressed(e -> activeKeys.add(e.getCode()));
        gameScene.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));
        window.setScene(gameScene);
        gameRunning = true;

        AnimationTimer fps60loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameRunning) update(now);
            }
        };
        fps60loop.start();
    }

    private void update(long now) {
        long currentTime = System.currentTimeMillis();

        physics.movePlayer1(player1, activeKeys.contains(KeyCode.W), activeKeys.contains(KeyCode.S), activeKeys.contains(KeyCode.A), activeKeys.contains(KeyCode.D));

        physics.movePlayer2(player2, activeKeys.contains(KeyCode.UP), activeKeys.contains(KeyCode.DOWN), activeKeys.contains(KeyCode.LEFT), activeKeys.contains(KeyCode.RIGHT));

        if (activeKeys.contains(KeyCode.F) && player1.canShoot(currentTime)) {
            shoot(player1, true);
        }

        if (activeKeys.contains(KeyCode.L) && player2.canShoot(currentTime)) {
            shoot(player2, false);
        }

        if (activeKeys.contains(KeyCode.DIGIT1)) player1.setWeapon(new Pistol());
        if (activeKeys.contains(KeyCode.DIGIT2)) player1.setWeapon(new Bow());
        if (activeKeys.contains(KeyCode.DIGIT3)) player1.setWeapon(new Cannon());
        if (activeKeys.contains(KeyCode.DIGIT4)) player1.setWeapon(new MagicWand());

        if (activeKeys.contains(KeyCode.DIGIT7)) player2.setWeapon(new Pistol());
        if (activeKeys.contains(KeyCode.DIGIT8)) player2.setWeapon(new Bow());
        if (activeKeys.contains(KeyCode.DIGIT9)) player2.setWeapon(new Cannon());
        if (activeKeys.contains(KeyCode.DIGIT0)) player1.setWeapon(new MagicWand());

        List<Projectile> removeBullet = new ArrayList<>();

        for (Projectile bullet : projectiles) {
            bullet.move();

            if (bullet.getX() < 0 || bullet.getX() > 800) {
                removeBullet.add(bullet);
                gameRoot.getChildren().remove(bullet.getView());
                continue;
            }

            if (physics.checkCollision(bullet, player1) && bullet.active && bullet.movingRight() == false) {
                player1.takeDamage(bullet.getDamage());
                bullet.active = false;
                removeBullet.add(bullet);
                gameRoot.getChildren().remove(bullet.getView());
            }

            if (physics.checkCollision(bullet, player2) && bullet.active && bullet.movingRight() == true) {
                player2.takeDamage(bullet.getDamage());
                bullet.active = false;
                removeBullet.add(bullet);
                gameRoot.getChildren().remove(bullet.getView());
            }
        }

        projectiles.removeAll(removeBullet);

        p1HealthLabel.setText("P1: " + player1.getHealth());
        p2HealthLabel.setText("P2: " + player2.getHealth());
        p1WeaponLabel.setText("Weapon: " + player1.getWeapon().getClass().getSimpleName());
        p2WeaponLabel.setText("Weapon: " + player2.getWeapon().getClass().getSimpleName());

        if (player1.getHealth() <= 0) endGame("Player 2 Wins!");
        if (player2.getHealth() <= 0) endGame("Player 1 Wins!");
    }

    private void shoot(Fighter shooter, boolean movingRight) {
        double startX = movingRight ? shooter.getX() + 45 : shooter.getX() - 10;
        double startY = shooter.getY() + 20;

        Projectile bullet = new Projectile(startX, startY, shooter.getWeapon().getSpeed(), shooter.getWeapon().getDamage(), movingRight);

        projectiles.add(bullet);
        gameRoot.getChildren().add(bullet.getView());
    }

    private void endGame(String message) {
        gameRunning = false;
        Label winLabel = new Label(message);
        winLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: green; -fx-font-weight: bold; -fx-background-color: white; -fx-padding: 10px;");
        winLabel.setLayoutX(250);
        winLabel.setLayoutY(250);
        gameRoot.getChildren().add(winLabel);
    }
}