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
            case "Mage": return new Mage(x, y);
            case "Archer": return new Archer(x, y);
            default: return new Warrior(x, y);
        }
    }
}
