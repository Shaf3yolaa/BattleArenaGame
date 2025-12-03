package com.battlearena.battlearenagame.models;

public class Warrior extends Fighter {

    public Warrior(String name){
        super("name",100,18);
        this.weapon =new Pistol();
    }
}
