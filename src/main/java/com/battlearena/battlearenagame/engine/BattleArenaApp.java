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
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


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
    private Rectangle p1HealthBar;
    private Rectangle p2HealthBar;
    private Label p1WeaponLabel;
    private Label p2WeaponLabel;
    private Stage window;
    private Scene selectionScene;
    private Scene gameScene;
    private static final double SCREENWIDTH = 1000;
    private static final double SCREENHEIGHT = 700;

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Battle Arena Game");
        Label welcome = new Label("WELCOME TO BATTLE ARENA GAME!");
        welcome.setStyle("-fx-font-size: 28px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #FFD700;" + "-fx-alignment: center;");
        String[] fighters = {"Warrior", "Mage", "Archer"};

        Label p1 = new Label("Player 1:");
        p1.setTextFill(Color.WHITE);
        p1.setStyle("-fx-font-weight: bold;");
        ComboBox<String> p1Choice = new ComboBox<>();
        p1Choice.getItems().addAll(fighters);
        p1Choice.setValue("Select a Character");

        Label p2 = new Label("Player 2:");
        p2.setTextFill(Color.WHITE);
        p2.setStyle("-fx-font-weight: bold;");
        ComboBox<String> p2Choice = new ComboBox<>();
        p2Choice.getItems().addAll(fighters);
        p2Choice.setValue("Select a Character");

        Button startButton = new Button("LET'S FIGHT!");
        startButton.setPrefWidth(160);
        startButton.setOnAction(e -> startGame(p1Choice.getValue(), p2Choice.getValue()));

        Button aboutButton = new Button("ABOUT GAME");
        aboutButton.setPrefWidth(160);
        aboutButton.setOnAction(e -> showAboutInfo());

        Button exitButton = new Button("EXIT");
        exitButton.setPrefWidth(160);
        exitButton.setOnAction(e -> window.close());

        startButton.setOnAction(e -> showInstructions(p1Choice.getValue(), p2Choice.getValue()));

        VBox layout1 = new VBox(20, welcome, p1, p1Choice, p2, p2Choice, startButton, aboutButton, exitButton);
        layout1.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: linear-gradient(#202020, #404040);");

        selectionScene = new Scene(layout1, SCREENWIDTH, SCREENHEIGHT);
        window.setScene(selectionScene);
        window.show();
    }

    private void showAboutInfo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About Battle Arena Game");
        alert.setHeaderText("Developed By:");
        alert.setContentText(
                "Engine: Ahmed Elshafeey - Mazen Abdelmoniem" + "\nModels: Seif Eldin Tarek - Mohamed Osama " + "\nPhysics: Ezz Eldin Gamal - Eyad Eshba"
        );
        alert.showAndWait();
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

    private void showInstructions(String p1Type, String p2Type) {
        gameRoot = new Pane();
        gameRoot.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
        gameRoot.setStyle("-fx-background-color: linear-gradient(#202020, #404040);");

        Label p1Instr = new Label("PLAYER 1 Controls:\nW = Up\nS = Down\nA = Left\nD = Right\nF = Shoot");
        p1Instr.setTextFill(Color.WHITE);
        p1Instr.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-alignment: left;");
        p1Instr.setLayoutX(50);
        p1Instr.setLayoutY(SCREENHEIGHT / 2 - 100);

        Label p2Instr = new Label("PLAYER 2 Controls:\n↑ = Up\n↓ = Down\n← = Left\n→ = Right\nL = Shoot");
        p2Instr.setTextFill(Color.WHITE);
        p2Instr.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-alignment: right;");
        p2Instr.setLayoutX(SCREENWIDTH - 200);
        p2Instr.setLayoutY(SCREENHEIGHT / 2 - 100);

        Label pressKeyLabel = new Label("Press any key to start!");
        pressKeyLabel.setTextFill(Color.WHITE);
        pressKeyLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        pressKeyLabel.setLayoutX(SCREENWIDTH / 2 - 150);
        pressKeyLabel.setLayoutY(SCREENHEIGHT / 2 + 50);

        gameRoot.getChildren().addAll(p1Instr, p2Instr, pressKeyLabel);
        Scene instructionsScene = new Scene(gameRoot, SCREENWIDTH, SCREENHEIGHT);
        window.setScene(instructionsScene);
        instructionsScene.setOnKeyPressed(e -> showFight(p1Type, p2Type));
    }

    private void showFight(String p1Type, String p2Type) {
        gameRoot.getChildren().clear();

        Label fightLabel = new Label("FIGHT!");
        fightLabel.setTextFill(Color.RED);
        fightLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");
        fightLabel.setLayoutX(SCREENWIDTH/2-80);
        fightLabel.setLayoutY(SCREENHEIGHT/2-50);
        gameRoot.getChildren().add(fightLabel);

        new Thread(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException ex) {}
            javafx.application.Platform.runLater(() -> startGame(p1Type, p2Type));
        }).start();
    }

    private void startGame(String p1Type, String p2Type) {
        gameRoot = new Pane();
        gameRoot.setPrefSize(SCREENWIDTH, SCREENHEIGHT);
        physics = new PhysicsEngine(SCREENWIDTH, SCREENHEIGHT);

        player1 = createFighter(p1Type, 60, SCREENHEIGHT / 2);
        player2 = createFighter(p2Type, SCREENWIDTH - 60, SCREENHEIGHT / 2);

        Line separator = new Line(SCREENWIDTH / 2, 0, SCREENWIDTH / 2, SCREENHEIGHT);

        p1HealthLabel = new Label(player1.getName() + ": " + player1.getHealth());
        p1HealthLabel.setLayoutX(20);
        p1HealthLabel.setLayoutY(20);
        p1HealthLabel.setStyle("-fx-font-weight: bold;");
        p1HealthBar = new Rectangle(200, 20, Color.GREEN);
        p1HealthBar.setLayoutX(20);
        p1HealthBar.setLayoutY(45);
        p1HealthBar.setStroke(Color.BLACK);
        p1HealthBar.setStrokeWidth(2);


        p2HealthLabel = new Label(player2.getName() + ": " + player2.getHealth());
        p2HealthLabel.setLayoutX(SCREENWIDTH-p2HealthLabel.getWidth()-120);
        p2HealthLabel.setLayoutY(20);
        p2HealthLabel.setStyle("-fx-font-weight: bold;");
        p2HealthBar = new Rectangle(200, 20, Color.GREEN);
        p2HealthBar.setLayoutX(SCREENWIDTH-p2HealthBar.getWidth()-20);
        p2HealthBar.setLayoutY(45);
        p2HealthBar.setStroke(Color.BLACK);
        p2HealthBar.setStrokeWidth(2);


        p1WeaponLabel = new Label("Weapon: " + player1.getWeapon().getName());
        p1WeaponLabel.setLayoutX(20);
        p1WeaponLabel.setLayoutY(70);
        p1WeaponLabel.setStyle("-fx-font-weight: bold;");

        p2WeaponLabel = new Label("Weapon: " + player2.getWeapon().getName());
        p2WeaponLabel.setLayoutX(SCREENWIDTH-p2WeaponLabel.getWidth()-120);
        p2WeaponLabel.setLayoutY(70);
        p2WeaponLabel.setStyle("-fx-font-weight: bold;");

        gameRoot.getChildren().addAll(player1.getView(), player2.getView(), p1HealthLabel, p2HealthLabel,p1HealthBar, p2HealthBar, p1WeaponLabel, p2WeaponLabel, separator);
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
            shoot(player1);
        }

        if (activeKeys.contains(KeyCode.L) && player2.canShoot(currentTime)) {
            shoot(player2);
        }

        if (activeKeys.contains(KeyCode.DIGIT1)) player1.setWeapon(new Pistol());
        if (activeKeys.contains(KeyCode.DIGIT2)) player1.setWeapon(new Bow());
        if (activeKeys.contains(KeyCode.DIGIT3)) player1.setWeapon(new Cannon());
        if (activeKeys.contains(KeyCode.DIGIT4)) player1.setWeapon(new MagicWand());

        if (activeKeys.contains(KeyCode.DIGIT7)) player2.setWeapon(new Pistol());
        if (activeKeys.contains(KeyCode.DIGIT8)) player2.setWeapon(new Bow());
        if (activeKeys.contains(KeyCode.DIGIT9)) player2.setWeapon(new Cannon());
        if (activeKeys.contains(KeyCode.DIGIT0)) player2.setWeapon(new MagicWand());

        List<Projectile> removeBullet = new ArrayList<>();

        for (Projectile bullet : projectiles) {
            bullet.move();

            if (bullet.getX() < 0 || bullet.getX() > SCREENWIDTH || bullet.getY() < 0 || bullet.getY() > SCREENHEIGHT) {
                removeBullet.add(bullet);
                gameRoot.getChildren().remove(bullet.getView());
                continue;
            }

            Fighter shooter = (Fighter) bullet.getView().getUserData();
            if (physics.checkCollision(bullet, player1) && bullet.active && shooter != player1) {
                player1.takeDamage(bullet.getDamage());
                bullet.active = false;
                removeBullet.add(bullet);
                gameRoot.getChildren().remove(bullet.getView());
            }

            if (physics.checkCollision(bullet, player2) && bullet.active && shooter != player2) {
                player2.takeDamage(bullet.getDamage());
                bullet.active = false;
                removeBullet.add(bullet);
                gameRoot.getChildren().remove(bullet.getView());
            }
        }

        projectiles.removeAll(removeBullet);

        p1HealthLabel.setText(player1.getName() + ": " + player1.getHealth());
        p2HealthLabel.setText(player2.getName() + ": " + player2.getHealth());
        double p1HealthPercent = (double) player1.getHealth() / player1.getMaxHealth();
        double p2HealthPercent = (double) player2.getHealth() / player2.getMaxHealth();
        p1HealthBar.setWidth(200 * p1HealthPercent);
        p2HealthBar.setWidth(200 * p2HealthPercent);
        p1HealthBar.setFill(p1HealthPercent > 0.5 ? Color.GREEN : (p1HealthPercent > 0.25 ? Color.ORANGE : Color.RED));
        p2HealthBar.setFill(p2HealthPercent > 0.5 ? Color.GREEN : (p2HealthPercent > 0.25 ? Color.ORANGE : Color.RED));
        p1WeaponLabel.setText("Weapon: " + player1.getWeapon().getName());
        p2WeaponLabel.setText("Weapon: " + player2.getWeapon().getName());

        if (player1.getHealth() <= 0) endGame(player2.getName() + " (P2) Wins!");
        if (player2.getHealth() <= 0) endGame(player1.getName() + " (P1) Wins!");
    }

    private void shoot(Fighter shooter) {
        double startX = shooter.getX() + shooter.getView().getWidth() / 2;
        double startY = shooter.getY() + shooter.getView().getHeight() / 2;

        Projectile bullet = new Projectile(startX, startY, shooter.getWeapon().getSpeed(), shooter.getWeapon().getDamage(), shooter.getRotation(), shooter.getWeapon().getProjectileSize());
        bullet.getView().setUserData(shooter);
        projectiles.add(bullet);
        gameRoot.getChildren().add(bullet.getView());
    }

    private void endGame(String message) {
        gameRunning = false;
        Label winLabel = new Label(message);
        winLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: green; -fx-font-weight: bold; -fx-background-color: white; -fx-padding: 10px;");
        winLabel.setLayoutX(SCREENWIDTH/2-200);
        winLabel.setLayoutY(SCREENHEIGHT/2-50);

        Button restartBtn = new Button("Play Again");
        restartBtn.setPrefWidth(180);
        restartBtn.setLayoutX(SCREENWIDTH / 2 - 90);
        restartBtn.setLayoutY(SCREENHEIGHT / 2 - 40);
        restartBtn.setOnAction(e -> {
            projectiles.clear();
            activeKeys.clear();
            startGame(player1.getName(), player2.getName());
        });

        Button menuBtn = new Button("Return to Main Menu");
        menuBtn.setPrefWidth(180);
        menuBtn.setLayoutX(SCREENWIDTH / 2 - 90);
        menuBtn.setLayoutY(SCREENHEIGHT / 2 + 10);
        menuBtn.setOnAction(e -> {
            projectiles.clear();
            activeKeys.clear();
            window.setScene(selectionScene);
        });

        Button exitBtn = new Button("Exit");
        exitBtn.setPrefWidth(180);
        exitBtn.setLayoutX(SCREENWIDTH / 2 - 90);
        exitBtn.setLayoutY(SCREENHEIGHT / 2 + 60);
        exitBtn.setOnAction(e -> window.close());

        gameRoot.getChildren().addAll(winLabel, restartBtn, menuBtn, exitBtn);
    }
}
